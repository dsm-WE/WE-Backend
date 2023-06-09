package com.we.webackend.infra.email

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import javax.mail.internet.MimeMessage


@Service
class EmailUtil(
    private val jms: JavaMailSender,
    private val emailProperty: EmailProperty
) {

    fun sendEmail(targetEmail: String, title: String, content: String) {
        val message: MimeMessage = jms.createMimeMessage()

        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setFrom(emailProperty.username)
        helper.setSubject(title)
        helper.setTo(targetEmail)
        helper.setText(content, false)

        jms.send(message)
    }


}
