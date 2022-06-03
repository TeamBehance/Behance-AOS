package com.sopt.behance_aos.ui.create

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.sopt.behance_aos.R
import com.sopt.behance_aos.data.MediaStoreImage
import com.sopt.behance_aos.data.RetrofitBuilder.createService
import com.sopt.behance_aos.model.response.ResponseFile
import com.sopt.behance_aos.ui.create.adpater.GalleryAdapter
import com.sopt.behance_aos.ui.create.title.BehanceTitleActivity
import com.sopt.behance_aos.util.GridItemSpaceDecoration
import com.sopt.behance_aos.util.MultiPartResolver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class BehanceCreateActivity : AppCompatActivity() {


    private lateinit var binding: com.sopt.behance_aos.databinding.ActivityBehanceCreateBinding

    private val images = MutableLiveData<List<MediaStoreImage>>()

    private var fileUri: Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) { // 권한 획득 성공 시
                // 갤러리 이미지 띄우기
                initAdapter()

            } else { // 권한 획득 거부 시
                Toast.makeText(this, "갤러리에 접근하기 위해서는 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_behance_create)
        backEvent()
        requestPermission()

        nextBtn()
    }


    // 권한 체크 함수
    private fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {  // 이미 권한부여를 받았기에 권한이 필요한 작업 수행
                // 갤러리 이미지 띄우기
                initAdapter()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> { // 권한 요청 권유
                showInContextUI()
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)   // 바로 권한요청하는 경우
            }
        }

    }

    private fun showInContextUI() {
        AlertDialog.Builder(this)
            .setTitle("권한 동의 필요")
            .setMessage("갤러리에 접근하기 위해서는 권한이 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            .setNegativeButton("거부") { _, _ ->
                Toast.makeText(this, "갤러리에 접근하기 위해서는 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

    // 리사이클러뷰 연결
    private fun initAdapter() {
        val galleryAdapter = GalleryAdapter()
        binding.rvCreateGallery.also { it ->
            it.layoutManager = GridLayoutManager(this, 4) // 아이템 개수 4개
            it.addItemDecoration(GridItemSpaceDecoration(2, 1)) // 아이템 사이에 간격 넣기
            it.adapter = galleryAdapter // 어댑터 연결
        }

        images.observe(this) { images ->
            galleryAdapter.submitList(images)
        }

        showImages() // 이미지 데이터 연결

        // 이미지 클릭 이벤트
        galleryAdapter.setOnItemClickListener(object : GalleryAdapter.OnItemClickListener {
            override fun onItemClick(v: View, uri: Uri, pos: Int) {
                if (binding.ivCreateSquare.visibility != View.INVISIBLE) { // 안내 메세지 숨기기
                    hideContent()
                }
                Glide.with(this@BehanceCreateActivity).load(uri).into(binding.ivCreateSelectedPhoto)
                fileUri = uri
            }
        })
    }

    private fun showImages() {
        GlobalScope.launch {
            val imageList = queryImages()
            images.postValue(imageList)
        }
    }

    private suspend fun queryImages(): List<MediaStoreImage> {
        val images = mutableListOf<MediaStoreImage>()

        withContext(Dispatchers.IO) {
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN
            )
            val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
            val selectionArgs = arrayOf(
                dateToTimestamp(day = 1, month = 1, year = 1970).toString()
            )

            val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
            contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null, // selection
                null, // selectionArgs
                sortOrder
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val dateTakenColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
                val displayNameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val dateTaken = Date(cursor.getLong(dateTakenColumn))
                    val displayName = cursor.getString(displayNameColumn)
                    val contentUri = Uri.withAppendedPath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id.toString()
                    )

                    val image = MediaStoreImage(id, displayName, dateTaken, contentUri)
                    images += image
                    Log.d(TAG, image.toString())
                }
            }
        }

        Log.d(TAG, "Found ${images.size} images")
        return images
    }


    @SuppressLint("SimpleDateFormat")
    private fun dateToTimestamp(day: Int, month: Int, year: Int): Long =
        SimpleDateFormat("dd.MM.yyyy").let { formatter ->
            formatter.parse("$day.$month.$year")?.time ?: 0
        }

    private fun backEvent() {
        binding.btnCreateBack.setOnClickListener {
            finish()
        }
    }

    // 안내 이미지 숨기기
    private fun hideContent() {
        binding.apply {
            ivCreateSquare.visibility = View.INVISIBLE
            tvCreateToolInfo.visibility = View.INVISIBLE
            tvCreateProjectStart.visibility = View.INVISIBLE
            tvCreateNext.setTextColor(Color.BLACK)
            tvCreateNext.isEnabled = true
            tvCreateReOrganization.setTextColor(Color.BLACK)
        }
    }

    // 파일 업로드
    private fun nextBtn() {
        binding.tvCreateNext.setOnClickListener {
            // 파일 업로드 서버 통신
            val file = MultiPartResolver(this).createImgMultiPart(fileUri!!)
            val call = createService.postFile(file)

            call.enqueue(object : Callback<ResponseFile> {
                override fun onResponse(
                    call: Call<ResponseFile>,
                    response: Response<ResponseFile>
                ) {
                    if (response.isSuccessful) { // 파일 생성 성공

                        val intent = Intent(this@BehanceCreateActivity,BehanceTitleActivity::class.java)
                        intent.putExtra("id",response.body()?.data?._id) // 통신으로 받은 유저 id값 넘겨주기

                        startActivity(intent)

                        finish()
                    }

                }

                override fun onFailure(call: Call<ResponseFile>, t: Throwable) { // 서버 통신에러
                    Log.e("NetworkTest", "error:$t")
                }

            })

        }
    }


}