package cn.itcast.f13_final_application.entity;

/**
 * Author: hongweiyu
 * Date: 2022/6/13
 * Email: yuxinburendavie@gmail.com
 * Description :
 */
public class PhoneQueryResult {
   private String resultcode;
   private String reason;
   private PhoneDetailInfo  result;
   private int error_code;
   public PhoneDetailInfo info;

   public String getResultcode() {
      return resultcode;
   }

   public void setResultcode(String resultcode) {
      this.resultcode = resultcode;
   }

   public String getReason() {
      return reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }

   public PhoneDetailInfo getResult() {
      return result;
   }

   public void setResult(PhoneDetailInfo result) {
      this.result = result;
   }

   public int getError_code() {
      return error_code;
   }

   public void setError_code(int error_code) {
      this.error_code = error_code;
   }
}
