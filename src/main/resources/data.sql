INSERT INTO chi_nhanh (dia_chi, email, so_dien_thoai, ten_kho)
VALUES 
('DiaChi1', 'email1@example.com', '123456789', 'Kho 1'),
('DiaChi2', 'email2@example.com', '987654321', 'Kho 2'),
('DiaChi3', 'email3@example.com', '111222333', 'Kho 3'),
('DiaChi4', 'email4@example.com', '444555666', 'Kho 4'),
('DiaChi5', 'email5@example.com', '777888999', 'Kho 5'),
('DiaChi6', 'email6@example.com', '222333444', 'Kho 6'),
('DiaChi7', 'email7@example.com', '555666777', 'Kho 7'),
('DiaChi8', 'email8@example.com', '888999000', 'Kho 8'),
('DiaChi9', 'email9@example.com', '999000111', 'Kho 9'),
('DiaChi10', 'email10@example.com', '333444555', 'Kho 10');

GO 

INSERT INTO khach_hang (dia_chi, email, so_dien_thoai, ten_kh)
VALUES 
('DiaChi1', 'khachhang1@example.com', '123456789', 'Khach Hang 1'),
('DiaChi2', 'khachhang2@example.com', '987654321', 'Khach Hang 2'),
('DiaChi3', 'khachhang3@example.com', '111222333', 'Khach Hang 3'),
('DiaChi4', 'khachhang4@example.com', '444555666', 'Khach Hang 4'),
('DiaChi5', 'khachhang5@example.com', '777888999', 'Khach Hang 5'),
('DiaChi6', 'khachhang6@example.com', '222333444', 'Khach Hang 6'),
('DiaChi7', 'khachhang7@example.com', '555666777', 'Khach Hang 7'),
('DiaChi8', 'khachhang8@example.com', '888999000', 'Khach Hang 8'),
('DiaChi9', 'khachhang9@example.com', '999000111', 'Khach Hang 9'),
('DiaChi10', 'khachhang10@example.com', '333444555', 'Khach Hang 10');

GO 

INSERT INTO nha_cung_cap (dia_chi, email, so_dien_thoai, ten_ncc)
VALUES 
('DiaChiNCC1', 'nhacungcap1@example.com', '123456789', 'NCC 1'),
('DiaChiNCC2', 'nhacungcap2@example.com', '987654321', 'NCC 2'),
('DiaChiNCC3', 'nhacungcap3@example.com', '111222333', 'NCC 3'),
('DiaChiNCC4', 'nhacungcap4@example.com', '444555666', 'NCC 4'),
('DiaChiNCC5', 'nhacungcap5@example.com', '777888999', 'NCC 5'),
('DiaChiNCC6', 'nhacungcap6@example.com', '222333444', 'NCC 6'),
('DiaChiNCC7', 'nhacungcap7@example.com', '555666777', 'NCC 7'),
('DiaChiNCC8', 'nhacungcap8@example.com', '888999000', 'NCC 8'),
('DiaChiNCC9', 'nhacungcap9@example.com', '999000111', 'NCC 9'),
('DiaChiNCC10', 'nhacungcap10@example.com', '333444555', 'NCC 10');

GO
-- Tạo bảng tạm để lưu trữ danh sách chi nhánh và số lượng tài khoản cần tạo
DECLARE @ChiNhanhTaiKhoan TABLE (chi_nhanh_id BIGINT, so_luong_tai_khoan INT);

-- Chèn dữ liệu giả mạo cho số lượng tài khoản cho mỗi chi nhánh
INSERT INTO @ChiNhanhTaiKhoan (chi_nhanh_id, so_luong_tai_khoan)
VALUES 
(1, 8), -- Ví dụ: Chi nhánh 1 có từ 5 đến 8 tài khoản
(2, 6), -- Ví dụ: Chi nhánh 2 có từ 5 đến 6 tài khoản
(3, 10); -- Ví dụ: Chi nhánh 3 có từ 5 đến 10 tài khoản
-- Tạo dữ liệu cho bảng nhan_vien dựa trên danh sách chi nhánh và số lượng tài khoản
INSERT INTO nhan_vien (ngay_sinh, trang_thai, chi_nhanh_id, chuc_vu, dia_chi, ho_ten, mat_khau, so_dien_thoai, tai_khoan)
SELECT
    DATEADD(YEAR, -30, GETDATE()), -- Ngày sinh giả mạo
    1, -- Trạng thái giả mạo
    c.chi_nhanh_id,
    CASE WHEN RAND() > 0.5 THEN 'USER' ELSE 'ADMIN' END, -- Chọn ngẫu nhiên chức vụ USER hoặc ADMIN
    'DiaChiNV' + CONVERT(NVARCHAR(10), ROW_NUMBER() OVER (PARTITION BY c.chi_nhanh_id ORDER BY (SELECT NULL))), -- Địa chỉ giả mạo
    'HoTenNV' + CONVERT(NVARCHAR(10), ROW_NUMBER() OVER (PARTITION BY c.chi_nhanh_id ORDER BY (SELECT NULL))), -- Họ tên giả mạo
    '$2a$10$1S9iI4EzIlzlm5C1rNT4M.svO6NvJNjqNGEuyQ/gAejHQ8LDFOsYG', -- Mật khẩu giả mạo
    '123456789', -- Số điện thoại giả mạo
    'tk_' + SUBSTRING(CONVERT(NVARCHAR(40), NEWID()), 1, 8) -- Tài khoản giả mạo
FROM @ChiNhanhTaiKhoan c
CROSS APPLY (SELECT TOP (c.so_luong_tai_khoan) 1 AS n FROM master.dbo.spt_values) AS numbers;
GO
DECLARE @TenVatTuDien TABLE (id INT IDENTITY(1,1), ten_vat_tu NVARCHAR(255), mo_ta NVARCHAR(MAX));
INSERT INTO @TenVatTuDien (ten_vat_tu, mo_ta)
VALUES 
('Quần', 'Mô tả về quần'),
('Áo', 'Mô tả về áo'),
('Giày', 'Mô tả về giày'),
('Túi xách', 'Mô tả về túi xách'),
('Đồng hồ', 'Mô tả về đồng hồ'),
('Kính mát', 'Mô tả về kính mát'),
('Balo', 'Mô tả về balo'),
('Mũ', 'Mô tả về mũ'),
('Bình nước', 'Mô tả về bình nước'),
('Gối', 'Mô tả về gối');
INSERT INTO vat_tu (gia, so_luong_ton, mo_ta, ten_vat_tu)
SELECT 
    RAND() * 100 + 50, -- Giá ngẫu nhiên từ 50 đến 150
    CAST(RAND() * 50 AS INT) + 20, -- Số lượng tồn ngẫu nhiên từ 20 đến 70
    d.mo_ta,
    d.ten_vat_tu
FROM @TenVatTuDien d;
GO
-- Tạo bảng danh sách vật tư và chi nhánh
DECLARE @VatTuChiNhanh TABLE (chi_nhanh_id BIGINT, vat_tu_id BIGINT);
-- Lấy danh sách vật tư và chi nhánh từ bảng vat_tu
INSERT INTO @VatTuChiNhanh (chi_nhanh_id, vat_tu_id)
SELECT TOP 30 chi_nhanh.id, vat_tu.id
FROM chi_nhanh
CROSS JOIN vat_tu
ORDER BY NEWID(); -- Sắp xếp ngẫu nhiên
-- Tạo bảng vat_tu_chi_nhanh từ danh sách vật tư và chi nhánh
INSERT INTO vat_tu_chi_nhanh (chi_nhanh_id, so_luong, vat_tu_id)
SELECT
    vtcn.chi_nhanh_id,
    FLOOR(RAND() * 50) + 10, -- Số lượng ngẫu nhiên từ 10 đến 60
    vtcn.vat_tu_id
FROM @VatTuChiNhanh vtcn;
GO