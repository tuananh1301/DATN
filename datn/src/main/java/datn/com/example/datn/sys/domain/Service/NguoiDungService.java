package datn.com.example.datn.sys.domain.Service;

import datn.com.example.datn.sys.domain.Dto.Request.NguoiDungReq;
import datn.com.example.datn.sys.domain.Dto.Request.NguoiDungUpdateRequest;
import datn.com.example.datn.sys.domain.Dto.Response.NguoiDungRes;

public interface NguoiDungService {
    NguoiDungRes createCustomer(NguoiDungReq nguoiDungReq);
    NguoiDungRes createStaff(NguoiDungReq nguoiDungReq);
    NguoiDungRes updateCustomer(Integer id, NguoiDungUpdateRequest nguoiDungUpdateRequest);
}
