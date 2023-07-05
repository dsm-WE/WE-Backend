package com.we.webackend.global.file

import org.springframework.web.multipart.MultipartFile

interface FileUploader {

    fun uploadFile(path: String, file: MultipartFile): String

}
