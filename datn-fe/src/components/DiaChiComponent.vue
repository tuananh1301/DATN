<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Địa Chỉ Giao Hàng & Tính Phí</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 20px;
      }
      .container {
        max-width: 500px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
      }
      .form-group {
        margin-bottom: 15px;
      }
      label {
        font-weight: bold;
      }
      label.required::after {
        content: " *";
        color: red;
      }
      input[type="text"],
      select {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        box-sizing: border-box;
      }
      button {
        background-color: #ff0000;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
      }
      button:hover {
        background-color: #cc0000;
      }
      #shippingFee {
        font-weight: bold;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h2>ĐỊA CHỈ GIAO HÀNG</h2>
      <form>
        <div class="form-group">
          <label class="required">HỌ TÊN</label>
          <input type="text" id="fullName" placeholder="Nhập họ tên của bạn" />
        </div>
        <div class="form-group">
          <label class="required">SĐT</label>
          <input type="text" id="phone" placeholder="Nhập số điện thoại" />
        </div>
        <div class="form-group">
          <label class="required">EMAIL</label>
          <input type="text" id="email" placeholder="Nhập email" />
        </div>
        <div class="form-group">
          <label class="required">THÀNH / THỊ</label>
          <select id="province">
            <option value="">Chọn Tỉnh/Thành Phố</option>
          </select>
        </div>
        <div class="form-group">
          <label class="required">QUẬN / HUYỆN</label>
          <select id="district" disabled>
            <option value="">Chọn Quận/Huyện</option>
          </select>
        </div>
        <div class="form-group">
          <label class="required">PHƯỜNG / XÃ</label>
          <select id="ward" disabled>
            <option value="">Chọn Phường/Xã</option>
          </select>
        </div>
        <div class="form-group">
          <label class="required">ĐỊA CHỈ</label>
          <input type="text" id="address" placeholder="Nhập địa chỉ của bạn" />
        </div>
        <div class="form-group">
          <input type="checkbox" id="defaultAddress" />
          <label for="defaultAddress">Đặt làm địa chỉ mặc định</label>
        </div>
        <div class="form-group">
          <button type="button" id="saveBtn">Lưu thông tin</button>
        </div>
        <div class="form-group">
          <p>Phí Giao Hàng: <span id="shippingFee">0 VNĐ</span></p>
        </div>
      </form>
    </div>

    <script>
      let provincesIDValue = null;
      let districtsIDValue = null;

      $(document).ready(function () {
        // Lấy danh sách tỉnh/thành phố
        $.ajax({
          url: "https://online-gateway.ghn.vn/shiip/public-api/master-data/province",
          type: "POST",
          dataType: "json",
          headers: {
            "Content-Type": "application/json",
            Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
          },
          success: function (response) {
            const provinces = response.data;
            const provinceSelect = $("#province");
            provinces.forEach(function (province) {
              provinceSelect.append(
                "<option value='" +
                  province.ProvinceID +
                  "'>" +
                  province.ProvinceName +
                  "</option>"
              );
            });
          },
          error: function (xhr, status, error) {
            console.log("Lấy tỉnh thất bại:", xhr.responseText);
          },
        });

        // Lấy danh sách quận/huyện khi chọn tỉnh
        $("#province").change(function () {
          const selectedProvinceId = $(this).val();
          provincesIDValue = selectedProvinceId;

          $("#district")
            .prop("disabled", true)
            .empty()
            .append("<option value=''>Chọn Quận/Huyện</option>");
          $("#ward")
            .prop("disabled", true)
            .empty()
            .append("<option value=''>Chọn Phường/Xã</option>");
          $("#shippingFee").text("0 VNĐ");

          if (selectedProvinceId) {
            $.ajax({
              url: "https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
              type: "GET",
              dataType: "json",
              headers: {
                "Content-Type": "application/json",
                Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
              },
              data: { province_id: selectedProvinceId },
              success: function (response) {
                const districts = response.data;
                const districtSelect = $("#district");
                districts.forEach(function (district) {
                  districtSelect.append(
                    "<option value='" +
                      district.DistrictID +
                      "'>" +
                      district.DistrictName +
                      "</option>"
                  );
                });
                districtSelect.prop("disabled", false);
              },
              error: function (xhr, status, error) {
                console.log("Lấy quận/huyện thất bại:", xhr.responseText);
              },
            });
          }
        });

        // Lấy danh sách phường/xã khi chọn quận
        $("#district").change(function () {
          const selectedDistrictId = $(this).val();
          districtsIDValue = selectedDistrictId;

          $("#ward")
            .prop("disabled", true)
            .empty()
            .append("<option value=''>Chọn Phường/Xã</option>");
          $("#shippingFee").text("0 VNĐ");

          if (selectedDistrictId) {
            $.ajax({
              url: "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward",
              type: "GET",
              dataType: "json",
              headers: {
                "Content-Type": "application/json",
                Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
              },
              data: { district_id: selectedDistrictId },
              success: function (response) {
                const wards = response.data;
                const wardSelect = $("#ward");
                wards.forEach(function (ward) {
                  wardSelect.append(
                    "<option value='" +
                      ward.WardCode +
                      "'>" +
                      ward.WardName +
                      "</option>"
                  );
                });
                wardSelect.prop("disabled", false);
              },
              error: function (xhr, status, error) {
                console.log("Lấy phường/xã thất bại:", xhr.responseText);
              },
            });
          }
        });

        // Tính phí giao hàng khi chọn phường/xã
        $("#ward").change(function () {
          calculateShippingFee();
        });

        // Lưu thông tin khi nhấn nút
        $("#saveBtn").click(function () {
          const fullName = $("#fullName").val();
          const phone = $("#phone").val();
          const email = $("#email").val();
          const province = $("#province option:selected").text();
          const district = $("#district option:selected").text();
          const ward = $("#ward option:selected").text();
          const address = $("#address").val();
          const isDefault = $("#defaultAddress").is(":checked");

          if (
            fullName &&
            phone &&
            email &&
            province &&
            district &&
            ward &&
            address
          ) {
            const data = {
              fullName,
              phone,
              email,
              province,
              district,
              ward,
              address,
              isDefault,
            };
            localStorage.setItem("shippingAddress", JSON.stringify(data));
            alert("Thông tin đã được lưu!");
          } else {
            alert("Vui lòng điền đầy đủ thông tin!");
          }
        });

        function calculateShippingFee() {
          const toDistrictId = parseInt(districtsIDValue);
          const toWardCode = $("#ward").val();

          if (toDistrictId && toWardCode && !isNaN(toDistrictId)) {
            $.ajax({
              url: "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services",
              type: "POST",
              dataType: "json",
              headers: {
                "Content-Type": "application/json",
                Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
              },
              data: JSON.stringify({
                shop_id: 4642718,
                from_district: 1454,
                to_district: toDistrictId,
              }),
              success: function (response) {
                if (response.data && response.data.length > 0) {
                  const serviceId = response.data[0].service_id;
                  calculateShippingFeeWithService(
                    serviceId,
                    toDistrictId,
                    toWardCode
                  );
                } else {
                  $("#shippingFee").text("Không có dịch vụ vận chuyển phù hợp");
                }
              },
              error: function (xhr, status, error) {
                console.log("Lấy dịch vụ thất bại:", xhr.responseText);
                $("#shippingFee").text("Không thể lấy dịch vụ vận chuyển");
              },
            });
          } else {
            $("#shippingFee").text("Vui lòng chọn đầy đủ địa chỉ");
          }
        }

        function calculateShippingFeeWithService(
          serviceId,
          toDistrictId,
          toWardCode
        ) {
          $.ajax({
            url: "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee",
            type: "POST",
            dataType: "json",
            headers: {
              "Content-Type": "application/json",
              Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
              ShopId: 4642718,
            },
            data: JSON.stringify({
              from_district_id: 1454,
              from_ward_code: "21211",
              to_district_id: toDistrictId,
              to_ward_code: toWardCode,
              weight: 200,
              service_id: serviceId,
            }),
            success: function (response) {
              if (response.data && response.data.total) {
                const shippingFee = response.data.total;
                const formattedShippingFee = shippingFee.toLocaleString(
                  "vi-VN",
                  {
                    style: "currency",
                    currency: "VND",
                  }
                );
                $("#shippingFee").text(formattedShippingFee);
              } else {
                $("#shippingFee").text("Không thể tính phí");
              }
            },
            error: function (xhr, status, error) {
              console.log("Tính phí thất bại:", xhr.responseText);
              $("#shippingFee").text("Không thể tính phí giao hàng");
            },
          });
        }
      });
    </script>
  </body>
</html>
