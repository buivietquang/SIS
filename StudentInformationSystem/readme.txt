Nhóm 13

21010573: Nguyễn Thành Đạt - 21010573DatNT
21010614: Bùi Việt Quang - buivietquang - ngocanhbk
21010634: Nguyễn Văn Thành - Thanh21010634

|     |Tên                | Các công việc đã làm                 |
| --- | ---------------   | ------------------------------------ |
| 1   | Bùi Việt Quang    | *Thiết kế gói view ; *Thiết kế gói entity ; *Tìm kiếm hình ảnh cho JavaFX ; *Thiết kế gói controller,
      |                   | *Thiết kế gói properties; *Thiết kế gói util; *Thiết kế gói validate, * Thiết kế gói logic,* Thiết kế gói dao
| 2   | Nguyễn Thành Đạt  | *Thiết kế gói controller, *Viết database, *Sửa lỗi gói controller, *Fix lỗi các gói, * Viết báo cáo
| 3   | Nguyễn Văn Thành  | *Thiết kế gói controller, *file message error, * Viết database, *Add comment, * Connect jdbc


CÁC GÓI CÓ VÀ CHỨC NĂNG( Mô hình MVC )
	View : chứa các file .fxml là giao diện các màn hình 
	Controller : xử lý các màn hình
	Model: bao gồm các package 
	-	Logic: nhiệm vụ xử lý logic, nhào nặn dữ liệu. Bao gồm 2 phần:
		Interface: chứa các interface của các method
		Impl: implement các method trong class interface
	-	Dao: thao tác với Database. Bao gồm 2 phần:
		Interface: chứa các interface của các method
		Impl: implement các method trong file interface
	-	Entity: gồm các class chứa thông tin thuộc tính các đối tượng
	-	Util: 
		Common.java: Chứa các hàm common của hệ thống( thông dụng, hay sử dụng) 
		Constant.java: Chứa các constants của hệ thống(hằng số).
	-	Validate: Chứa các class kiểm tra dữ liệu ( độ hợp lệ, check tồn tại)
	-	Properties: chứa class đọc file .properties, thường lưu message, các cấu hình config, thông tin về DB…
	
	
Ngày 14 - 04 - 2023: Commit thêm gói properties, gói util, gói controller , sửa lỗi controller
Ngày 19 - 04 - 2023: Commit gói validate 
Ngày 20 - 04 - 2023: Hoàn thiện database , gói logic(chưa impl)
Ngày 23 - 04 - 2023: Hoàn thiện database , gói impl của logic
Ngày 25 - 04 - 2023: Gói dao(chưa impl)
Ngày 27 - 04 - 2023: Gói dao thêm impl
Ngày 01 - 05 - 2023: Fix lỗi dao
Ngày 05 - 05 - 2023: fix lỗi login của phần mềm 
Ngày 07 - 05 - 2023: Fix lỗi mã hóa mật khẩu
Ngày 10 - 05 - 2023: Hoàn thiện các chức năng, viết báo cáo
Ngày 15 - 05 - 2023: Fix thông báo 
Ngày 20 - 05 - 2023: Hoàn thiện báo cáo



