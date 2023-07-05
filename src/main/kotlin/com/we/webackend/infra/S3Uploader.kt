package com.we.webackend.infra

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.Region
import com.amazonaws.util.IOUtils
import com.we.webackend.global.exception.data.ErrorCode
import com.we.webackend.global.exception.data.BusinessException
import com.we.webackend.global.file.FileUploader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream

@Service
class S3Uploader(
    private val s3: AmazonS3
): FileUploader {

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val bucketName = "test-we-test"

    override fun uploadFile(path: String, file: MultipartFile): String {
        val objectMetadata = ObjectMetadata()
        val bytes: ByteArray = IOUtils.toByteArray(file.inputStream)
        objectMetadata.contentLength = bytes.size.toLong()
        objectMetadata.contentType = file.contentType
        val byteArrayInputStream = ByteArrayInputStream(bytes)

        val fileName = "$bucketName/${path}/${file.originalFilename}"

        try {
            s3.putObject(PutObjectRequest(bucketName, fileName, byteArrayInputStream, objectMetadata))
        } catch (err: Exception) {
            log.error(err.message)
            throw BusinessException(errorCode = ErrorCode.BAD_GATEWAY_ERROR)
        }
        return "https://$bucketName.s3.ap-northeast-2.amazonaws.com/$fileName"
    }

}
