package by.com.lifetech.billingapi.models.entity;

public interface FinHistAttrs {
    String getTypeCode();
    String getReasonCode();
    String getBankBranchCode();
    String getBankCode();
    void setTypeName(String typeName);
    void setReasonName(String reasonName);
    void setBankBranchName(String bankBranch);
    void setBankName(String bankCode);
}
