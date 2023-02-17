package com.hend.calldetailsrecorder.domain

interface IUploadDetlsCallUC {
    fun sentRingingAction()
    fun sentAnswerAction()
    fun sentCloseAction()
    fun sentNoAnswerAction()
}