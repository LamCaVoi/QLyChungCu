CREATE TABLE IF NOT EXISTS TienTro (
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    IDPhong int,
    Discount INT,
    Date_Time VARCHAR(15),
    So_Nuoc INT,
    So_Dien INT,
    Service_Fee BIGINT,
    Total BIGINT
);
CREATE TABLE IF NOT EXISTS Phong (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Dien_Tich DOUBLE,
    Gia_Tien BIGINT,
    Trang_Thai VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NguoiThue (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Ten VARCHAR(255),
    So_Dien_Thoai VARCHAR(50),
    Ngay_Thang_Nam_Sinh VARCHAR(50),
    Que_Quan VARCHAR(100),
    So_CCCD VARCHAR(15),
    ID_Phong INT
);

CREATE TABLE IF NOT EXISTS HopDong (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ID_Phong INT,
    ID_Nguoi_Thue INT,
    Ngay_Ky_Hop_Dong VARCHAR(50),
    Thoi_Han_Hop_Dong INT,
    Tien_Dat_Coc BIGINT,
    Ngay_Het_Hop_Dong VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS RoomPic (
    ID_Phong int,
    PIC BLOB
);

CREATE TABLE IF NOT EXISTS Users(
    UserName Varchar(255),
    Pass Varchar(255)

)