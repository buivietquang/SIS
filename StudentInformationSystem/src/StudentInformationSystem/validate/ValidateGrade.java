package StudentInformationSystem.validate;

import javafx.scene.control.TextField;

public class ValidateGrade {
	/**
	 * Check valid when admin update grade 
	 * Kiểm tra hợp lệ khi quản trị viên cập nhật điểm
	 * @param tfmiddleGrade middle grade
	 * @param tffinalGrade final grade
	 * @return error string
	 */
	public String vailidateUpdateGrade(TextField tfmiddleGrade, TextField tffinalGrade) {
		
		float middleGrade = 0, finalGrade = 0;		
		String errStr = "";
		
	    try{
	    	middleGrade = Float.parseFloat(tfmiddleGrade.getText());
	    	finalGrade = Float.parseFloat(tffinalGrade.getText());
	    }catch(NumberFormatException e){
	        errStr = "Chuỗi bạn vừa nhập vào không phải là số";
	        return errStr;
	    }
		
		if(checkEmpty(tfmiddleGrade.getText())){
			errStr = "Điểm giữa kỳ không được để trống";
			return errStr;
		}
		else if(checkEmpty(tffinalGrade.getText())){
			errStr = "Điểm cuối kỳ không được để trống";
			return errStr;
		}
		else if(middleGrade < 0 || middleGrade > 10) {
			errStr = "Điểm giữa kỳ không được nhỏ hơn 0 và lớn hơn 10";
			return errStr;
		}
		else if(finalGrade < 0 || finalGrade > 10) {
			errStr = "Điểm cuối kỳ không được nhỏ hơn 0 và lớn hơn 10";
			return errStr;
		}
			
		return errStr;
	}
	
	/**
	 * Check if string is empty
	 * Kiểm tra nếu string bị trống
	 * @param str a input string
	 * @return true if it empty
	 */
	public boolean checkEmpty(String str) {
		if(str.equals(""))
			return true;
		return false;
	}
}
