package StudentInformationSystem.validate;

import java.util.regex.Pattern;

import StudentInformationSystem.util.Constants;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ValidateSubject {
	
	/**
	 * Check before CRUD subject
	 * @param tfsubjectCode subject code
	 * @param tfsubjectName subject name
	 * @param tfcreditSubject credit of subject
	 * @param tfcreditTuition credit tuition
	 * @param tfinstitute institute name
	 * @param tfweight weight of subject
	 * @return a error string
	 */
	public String vailidateUpdateSubject(TextField tfsubjectCode, TextField tfsubjectName, 
			TextField tfcreditSubject,TextField tfcreditTuition, 
			ComboBox<String> tfinstitute, TextField tfweight) {
		
		String errStr = "";
		int creditSubject = 0, creditTuition = 0;
		float weight;
		
		if(checkEmpty(tfsubjectCode.getText())) {
			errStr = "Mã học phần không được để trống";
	        return errStr;
		}
		else if(checkEmpty(tfsubjectName.getText())) {
			errStr = "Tên học phần không được để trống";
	        return errStr;
		}
		else if(checkEmpty(tfcreditSubject.getText())) {
			errStr = "Số tín chỉ không được để trống";
	        return errStr;
		}
		else if(checkEmpty(tfcreditTuition.getText())) {
			errStr = "Số tín chỉ học phí không được để trống";
	        return errStr;
		}
		else if(checkEmpty(tfinstitute.getSelectionModel().getSelectedItem())) {
			errStr = "Khoa viện không được để trống";
	        return errStr;
		}
		else if(checkEmpty(tfweight.getText())) {
			errStr = "Trọng số không được để trống";
	        return errStr;
		}
		else if(checkEmpty(tfsubjectCode.getText())==false) {
			Pattern pattern = Pattern.compile(Constants.FORMAT_SUBJECTCODE);
			if(pattern.matcher(tfsubjectCode.getText()).matches()==false) {
				errStr = "Mã học phần không đúng định dạng";
		        return errStr;
			}
		}
		else if(creditSubject < 0 || creditSubject > 4) {
			errStr = "Số tín chỉ không được nhỏ hơn 0 và lớn hơn 4";
			return errStr;
		}
		else if(creditTuition < 0 || creditTuition > 9) {
			errStr = "Số tín chỉ không được nhỏ hơn 0 và lớn hơn 9";
			return errStr;
		}
		
		try{
	    	creditSubject = Integer.parseInt(tfcreditSubject.getText());
	    	creditTuition = Integer.parseInt(tfcreditTuition.getText());
	    	weight = Float.parseFloat(tfweight.getText());
	    }catch(NumberFormatException e){
	        errStr = "Chuỗi bạn vừa nhập vào không phải là số";
	        return errStr;
	    }
		
		if(weight < 0 || weight > 1) {
			errStr = "Trọng số không được nhỏ hơn 0 và lớn hơn 1";
			return errStr;
		}
		
		return errStr;
	}
		
	/**
	 * Check if string is empty
	 * @param str a input string
	 * @return true if it empty
	 */
	public boolean checkEmpty(String str) {
		if(str.equals(""))
			return true;
		return false;
	}
}
