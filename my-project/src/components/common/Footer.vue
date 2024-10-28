<template>
    <footer>
      <div class="container-footer">
        <div class="footer-logo">
          <img src="../../assets/images/logo.png" height="100px" alt="Logo">
          <span>
            <h4>ỦY BAN NHÂN DÂN THÀNH PHỐ ĐÀ NẴNG</h4>
            <p>Địa chỉ: 24 Trần Phú, Q. Hải Châu, TP. Đà Nẵng</p>
            <p>Điện thoại: * 1022, 1900 1022</p>
          </span>
        </div>
  
        <div class="footer-menu">
          <ul>
            <li><a href="#">Trang chủ</a></li>
            <li><a href="#">Hồ sơ công dân số</a></li>
            <li><a href="#">Dịch vụ từ Chính quyền</a></li>
            <li><a href="#">Dịch vụ từ Doanh nghiệp</a></li>
            <li><a href="#">Hỗ trợ</a></li>
          </ul>
        </div>
  
        <div class="footer-store">
          <p>Tải ứng dụng tại</p>
          <div class="store-icons">
            <a href="#"><img src="../../assets/images/ggplay.png" alt="Google Play"></a>
            <a href="#"><img src="../../assets/images/appstore.png" alt="App Store"></a>
          </div>
        </div>
      </div>
      <hr>
    </footer>
  </template>
  
  <script>
  export default {
    name: "FooterComponent",
    data(){
      return {
  
      }
    }
  }
  </script>
  
  <style>
  
  
  footer {
    background-color: #0056A3;
    padding: 30px;
    color: white;
    margin-top: 80px;
  }
  
  .footer-menu{
    margin-left: 180px;
  }
  
  .footer-logo  {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .footer-logo span {
    margin-left: 10px;
    line-height: 18.75px;
  }
  
  .footer-logo span h4 {
    margin: 5px 0;
  }
  
  .footer-logo span p {
    font-size: 16px;
    margin: 7px 0;
  }
  
  .container-footer{
    padding-left: 40px;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
    align-items: flex-start;
    justify-content: center;
  }
  
  .footer-menu ul li {
    list-style: none;
    padding-top: 10px;
  }
  
  .footer-menu ul li a {
    text-decoration: none;
    color: #fff;
  }
  
  .store-icons {
    margin-top: 10px;
  }
  
  .store-icons a:nth-of-type(2) {
    margin-left: 12px;
  }
  
  
  .footer-store{
    padding-left: 120px;
  }
  @media (max-width: 768px) {
    .container-footer {
      display: grid;
      grid-template-columns: 1fr;
      gap: 20px;
    }
  
    .footer-menu {
      text-align: center;
      margin-bottom: 20px;
      margin-right: 200px;
    }
  
    .footer-store {
      text-align: center;
      margin-bottom: 20px;
      justify-self: center;
      margin-right: 100px;
    }
  }
  
  @media (max-width: 480px) {
    .container-footer {
      display: grid;
      grid-template-columns: repeat(1, 1fr);
      gap: 5px;
  
    }
  
    .footer-menu, .footer-store {
      width: 100%;
      text-align: center;
      margin-top: 20px;
    }
  
    .footer-logo span h4 {
      font-size: 16px;
      margin: 5px 0 ;
    }
  
    .footer-logo span p {
      font-size: 13px;
      margin: 5px 0;
    }
  
    .footer-menu {
      margin-left: -20px;
    }
  
    .footer-store {
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 0;
      padding-right: 30px;
    }
  
    .store-icons {
      flex-direction: column;
      align-items: center;
    }
  
  
    .store-icons a {
      margin-left: 0;
      margin-bottom: 10px;
    }
  }
  
  hr {
    opacity: 0.5;
    margin-top: 15px;
  }
  </style>