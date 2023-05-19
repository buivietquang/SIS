
package StudentInformationSystem.validate;

import StudentInformationSystem.entity.Notice;

public class ValidateNotice {
	/**
	 * Check before CRUD a notice (Create - Read - Update - Delete)
	 * @param notice
	 * @return true if pass valid
	 */
	public boolean validateGetNotice(Notice notice) {
		if ((String.valueOf(notice.getIdnotice()).isEmpty()) || (notice.getTitle().isEmpty())
				|| (notice.getContent().isEmpty()) || (notice.getDatetime().isEmpty())) {
	        return false;
		}
		else {
			return true;
		}
	}

}
