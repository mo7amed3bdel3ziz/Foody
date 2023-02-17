package com.peter.foody.data.remote.model.models;

import java.util.List;

public class BillReturn {
  List<String>   QrCode ;
    String Status ;
    String submitted;
    List<String> ErrorMessage;
    String SubmitionID ;

    public List<String> getQrCode() {
        return QrCode;
    }

    public void setQrCode(List<String> qrCode) {
        QrCode = qrCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public List<String> getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getSubmitionID() {
        return SubmitionID;
    }

    public void setSubmitionID(String submitionID) {
        SubmitionID = submitionID;
    }
}
