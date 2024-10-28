<template>
  <div class="slider">
    <div class="list" ref="list">
      <div class="item" v-for="(image, index) in images" :key="index">
        <img :src="image" alt="">
      </div>
    </div>

    <div class="buttons">
      <button @click="prev" ref="prev">&lt;</button>
      <button @click="next" ref="next">&gt;</button>
    </div>

    <ul class="dots">
      <li v-for="(dot, index) in images" :key="index" :class="{ active: activeIndex === index }" @click="goToSlide(index)"></li>
    </ul>
  </div>

  <h1>Tiện ích từ chính quyền</h1>

  <div class="gallery">
    <div class="gallery-item" >
      <img src="@/assets/images/news1.png" alt="Image 1">
      <h3>DỊCH VỤ CÔNG</h3>
      <p>Tiếp cận và liên hệ đến các ban – ngành chính <br> quyền nhanh chóng để thực hiện, giải quyết các thủ <br> tục hành chính</p>
      <button>Truy cập</button>
    </div>
    <div class="gallery-item">
      <img src="@/assets/images/news2.png" alt="Image 2">
      <h3>GỢI Ý - PHẢN ÁNH</h3>
      <p> Phản ánh tình hình trật tự, an ninh, xã hội <br> trực tiếp đến cơ quan chức năng có thẩm <br>quyền</p>
      <button>Truy cập</button>
    </div>
    <div class="gallery-item" >
      <img src="@/assets/images/news3.png" alt="Image 3">
      <h3>KHẢO SÁT,ĐÁNH GIÁ</h3>
      <p>Đánh giá chất lượng phục vụ của cơ quan hành<br> chính nhà nước và của cán bộ công chức, viên chức <br>trong giải quyết thủ tục hành chính tại TP Đà Nẵng</p>
      <button>Truy cập</button>
    </div>
  </div>

  <div class="gallery-second">
    <div class="gallery-item-2">
      <img src="@/assets/images/news4.png" alt="Img 6">
      <h3>DỮ LIỆU MỞ</h3>
      <p>Truy cập và tra cứu thông tin miễn phí về các vấn đề sức <br>khỏe, giáo dục, xã hội,.. được chính quyền chia sẻ. </p>
      <button>Truy cập</button>
    </div>

    <div class="gallery-item-2">
      <img src="@/assets/images/news5.png" alt="Img 7">
      <h3>ĐẶT LỊCH</h3>
      <p>Truy cập và tra cứu thông tin miễn phí về các vấn đề sức <br>khỏe, giáo dục, xã hội,.. được chính quyền chia sẻ. </p>
      <button>Truy cập</button>
    </div>
  </div>

  <div class="service">
    <div class="services-section">
      <h3>Các dịch vụ</h3>
      <div class="service-cards">
        <div class="service-card">
          <div class="icon icon1">
            <img src="@/assets/images/item1.png">
          </div>
          <div class="menu-item">
            <h3 class="font_color1">SỨC KHOẺ VÀ Y TẾ</h3>
            <p>Cung cấp thông tin thủ tục hành chính, <br> dịch vụ công trực tuyến liên quan cho trẻ<br> dưới 6 tuổi.</p>
          </div>
        </div>

        <div class="service-card">
          <div class="icon icon2">
            <img src="@/assets/images/item3.png">
          </div>
          <div class="menu-item">
            <h3 class="font_color2">PHƯƠNG TIỆN NGƯỜI LÁI</h3>
            <p>Cung cấp thông tin thủ tục hành chính,<br> dịch vụ công trực tuyến liên quan đến <br> quá trình lựa chọn nghề nghiệp, nơi làm<br> việc và quá trình làm việc của cá nhân.</p>
          </div>
        </div>

        <div class="service-card">
          <div class="icon icon3">
            <img src="@/assets/images/item5.png">
          </div>
          <div class="menu-item">
            <h3 class="font_color3">GIÁO DỤC</h3>
            <p>Cung cấp thông tin thủ tục hành chính,<br> dịch vụ công trực tuyến liên quan đến <br> việc học tập của cá nhân từ mầm non <br> đến sau đại học.</p>
          </div>
        </div>
        <div class="service-card">
          <div class="icon icon1">
            <img src="@/assets/images/item2.png">
          </div>
          <div class="menu-item">
            <h3 class="font_conlor1" >GIẤY TỜ TUỲ THÂN</h3>
            <p>Cung cấp thông tin thủ tục hành chính,<br> dịch vụ công trực tuyến liên quan đến <br> thường trú, tạm trú và cấp một số loại <br> giấy tờ tùy thân của cá nhân.</p>
          </div>
        </div>
        <div class="service-card">
          <div class="icon icon2">
            <img src="@/assets/images/item4.png">
          </div>
          <div class="menu-item">
            <h3 class="font_color2">ĐIỆN LỰC, NHÀ Ở, NHÀ ĐẤT</h3>
            <p>Cung cấp thông tin thủ tục hành chính,<br> dịch vụ công trực tuyến liên quan đến <br> quyền sở hữu, sử dụng tài sản là nhà ở,<br> đất đa và cung cấp điện năng</p>
          </div>
        </div>
        <div class="service-card">
          <div class="icon icon3">
            <img src="@/assets/images/item6.png">
          </div>
          <div class="menu-item">
            <h3 class="font_color3">DỊCH VỤ KHÁC</h3>
            <p>Cung cấp thông tin thủ tục hành chính,<br> dịch vụ công trực tuyến liên quan đến <br> việc thực hiện các quy định về hôn nhân <br> và gia đình.</p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <h2>Tin tức tiêu điểm</h2>

  <div class="news">
    <div class="news-item">
      <img class="img-news" src="@/assets/images/news6.png">
      <div class="tibo">
        <p class="bold" >Nỗ lực hết khả năng để xứng đáng với niềm <br>tin của cử tri</p>
        <div class="item-br">
          <img src="@/assets/images/icon1.png">
          <p>17/05/2021</p>
          <img src="@/assets/images/icon2.png" >
          <p>19:42</p>
        </div>
        <p class="bold2">
          Sáng 16-5, tại điểm cầu Trung tâm Hành chính thành phố,<br> Ban Thường trực Ủy ban Mặt trận Tổ quốc Việt Nam thành <br> phố tổ chức Hội nghị...
        </p>
      </div>
    </div>

    <div class="news-item" >
      <img class="img-news" src="@/assets/images/news7.png">
      <div class="tibo">
        <p class="bold" >Thưởng "nóng" 2 đơn vị có thành tích xuất <br> sắc trong phòng chống dịch</p>
        <div class="item-br" >
          <img src="@/assets/images/icon1.png" >
          <p>17/05/2021</p>
          <img src="@/assets/images/icon2.png" >
          <p>19:42</p>
        </div>
        <p class="bold2">
          Tại cuộc họp Ban chỉ  đạo phòng chống dịch thành phố <br> chiều ngày 13-5, Chủ tịch UBND thành phố Lê Trung Chinh<br> thông báo...
        </p>
      </div>

    </div>

    <div class="news-item">
      <img class="img-news" src="@/assets/images/news8.png">
      <div class="tibo">
        <p class="bold">Hàng ngàn người dân và du khách thưởng <br> thức cầu Rồng phun lửa, phun nước</p>
        <div class="item-br">
          <img src="@/assets/images/icon1.png">
          <p>17/05/2021</p>
          <img src="@/assets/images/icon2.png">
          <p>19:42</p>
        </div>
        <p class="bold2">
          Tối 30-4, hàng ngàn người dân và du khách đổ về khu vực <br> cầu Rồng và công viên dưới chân cầu để xem cầu Rồng <br> phun lửa, phun nước...
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeViews",
  data() {
    return {
      images: [
        require('@/assets/images/slide1.png'),
        require('@/assets/images/slide2.png'),
        require('@/assets/images/slide1.png'),
        require('@/assets/images/slide2.png')
      ],
      activeIndex: 0,
      refreshInterval: null
    }
  },
  methods: {
    next() {
      this.activeIndex = (this.activeIndex + 1) % this.images.length;
      this.reloadSlider();
    },
    prev() {
      this.activeIndex = (this.activeIndex - 1 + this.images.length) % this.images.length;
      this.reloadSlider();
    },
    goToSlide(index) {
      this.activeIndex = index;
      this.reloadSlider();
    },
    reloadSlider() {
      const slider = this.$refs.list;
      if (slider) {
        slider.style.transform = `translateX(-${this.activeIndex * 100}%)`;
      }

      clearInterval(this.refreshInterval);
      this.refreshInterval = setInterval(this.next, 3000);
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.reloadSlider();
      this.refreshInterval = setInterval(this.next, 3000);
    });
  },
  beforeUnmount() {
    clearInterval(this.refreshInterval);
  }
}
</script>

<style>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body{
  font-family: 'Roboto', sans-serif;
  font-size: 16px;
}

.slider {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
  margin-top: 0;
  display: flex;
}

.slider .list {
  display: flex;
  height: 100%;
  transition: transform 0.5s ease-in-out;
}

.slider .list .item {
  min-width: 100%;
  height: 100%;
}

.slider .list img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.slider .buttons {
  position: absolute;
  top: 50%;
  left: 5%;
  width: 90%;
  display: flex;
  justify-content: space-between;
  transform: translateY(-50%);
}

.slider .buttons button {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.3);
  color: #fff;
  border: none;
  font-family: monospace;
  font-weight: bold;
  font-size: 25px;
  cursor: pointer;
}

.slider .dots {
  position: absolute;
  bottom: 10px;
  left: 0;
  color: #fff;
  width: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
}

.slider .dots li {
  list-style: none;
  width: 10px;
  height: 10px;
  background-color: #fff;
  margin: 10px;
  border-radius: 20px;
  transition: 0.5s;
  cursor: pointer;
}

.slider .dots li.active {
  width: 30px;
}

@media screen and (max-width: 768px) {
  .slider {
    height: 60vh;
  }
}

h1{
  margin-top: 30px;
  text-align: center;
  margin-bottom: 20px;
  color: #0056A3;
  font-size: 36px;
}

.gallery {
  display: flex;
  flex-wrap: wrap-reverse;
  align-items: center;
  justify-content: center;
  gap: 50px;
  text-align: center;
}

.gallery img {
  width: 414px;
  height: 250px;
  object-fit: cover;
  border-radius: 10px;
}

.gallery-item {
  overflow: hidden;
  border-radius: 10px;
}

.gallery-item button {
  padding: 10px 20px;
  background: #0056A3;
  color: #fff;
  border-radius: 10px;
  border: 1px solid transparent;
  margin-top: 10px;
}

@media (max-width: 768px) {
  .gallery {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;

  }

  .gallery img{
    width: 300px;
    height: 150px;
    object-fit: cover;
    border-radius: 10px;
  }

  p {
    font-size: 16px;
    line-height: 1.4;
  }
}

@media (max-width: 480px) {
  .gallery {
    grid-template-columns: 1fr;
    justify-content: center;
  }

  .gallery img{
    width: 300px;
    height: 150px;
    object-fit: cover;
    border-radius: 10px;
    margin-top: 10px;
  }
}



.gallery-second {
  display: flex;
  flex-wrap: wrap-reverse;
  align-items: center;
  justify-content: center;
  gap: 50px;
  text-align: center;
  margin-top: 40px;
}

.gallery-second img {
  width: 414px;
  height: 250px;
  object-fit: cover;
  border-radius: 10px;
}

.gallery-item-2 {
  overflow: hidden;
  border-radius: 10px;
}

.gallery-item-2 button{
  padding: 10px 20px;
  background: #0056A3;
  color: #fff;
  border-radius: 10px;
  border: 1px solid transparent;
  margin-top: 10px;
}

@media (max-width: 768px) {
  .gallery-second {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }

  .gallery-second img {
    width: 300px;
    height: 150px;
    object-fit: cover;
    border-radius: 10px;
  }

  p {
    font-size: 14px;
    line-height: 1.4;
  }
}


.service{
  background: #F1F7FF;
  height: 500px;
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

h3{
  color: #0056A3;
}

.services-section {
  text-align: center;
}

.services-section h2 {
  margin-bottom: 20px;
  font-size: 28px;
}

.service-cards {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  margin-top: 20px;
}

.service-card {
  width: 414px;
  height: 143px;
  border: 1px solid transparent;
  border-radius: 5px;
  background-color: #ffffff;
  text-align: center;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

.services-section h3 {
  font-size: 35px;
}

.menu-item {
  text-align: left;
}

.menu-item h3 {
  font-size: 18px;
}

.menu-item p {
  font-weight: 300;
  opacity: 0.7;
  line-height: 22px;
  font-family: 'Roboto', sans-serif;
}

.service-card .icon {
  font-size: 40px;
  margin-bottom: 10px;
}

.font_color1{
  color: #FEC029;
}

.font_color2{
  color: #558776;
}

.font_color3 {
  color: #2B97F1;
}

.icon1{
  width: 70px;
  height: 70px;
  border-radius: 50%;
  background: #FEC029;
  display: flex;
  justify-content: center;
  align-items: center;
}

.icon1 img {
  height: 30px;
}

.icon2 {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  background: #558776;
  display: flex;
  justify-content: center;
  align-items: center;
}

.icon2 img {
  height: 30px;
}

h2{
  text-align: center;
  margin-top: 30px;
  color: #0056A3;
  font-size: 36px;
  font-weight: 600;
  line-height: 42.19px;
}

.icon3 {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  background: #2B97F1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.icon3 img {
  height: 30px;
}
.service-card h3 {
  font-size: 18px;
  color: #0056A3;
}

.service-card p {
  font-size: 16px;
  color: #333;
}

@media (max-width: 768px) {
  .service{
    height: 740px;
    margin-top: 20px;
  }

  .service-cards {
    padding: 30px;
    grid-template-columns: repeat(2, 1fr);
  }

  .service-card {
    width: 350px;
    height: 166px;
    background-color: #f9f9f9;
    text-align: center;
    border: 1px solid transparent;
    object-fit: cover;
  }

  .service-card h3{
    font-size: 15px;
  }

  .service-card p {
    font-size: 14px;
    line-height: 1.4;
  }
}


@media (max-width: 480px) {
  .service{
    height: 1250px;
    margin-top: 20px;
  }

  .service-card{
    width: 414px;
    height: 166px;
    border: 1px solid transparent;
  }

  .service-cards {
    grid-template-columns: 1fr;
  }

  .service-card h3{
    font-size: 15px;
  }

  .service-card p {
    font-size: 14px;
    line-height: 1.4;
  }
}


.item-br{
  display: flex;
  align-items: center;
  padding-top:5px;
  line-height: 18.75px;
}

.item-br img:first-of-type {
  height: 15px;
}

.item-br p:first-of-type {
  font-size: 16px;
  font-weight: bold;
  margin-left: 5px;
}

.item-br img:nth-of-type(2) {
  margin-left: 5px;
  height: 15px;
}

.item-br p:nth-of-type(2) {
  font-size: 16px;
  font-weight: bold;
  margin-left: 3px;
}

.news{
  display: flex;
  flex-wrap: wrap-reverse;
  align-items: center;
  justify-content: center;
  gap: 40px;
  text-align: center;
  margin-top: 40px;
}

.news-item .img-news {
  width: 414px;
  height: 250px;
  object-fit: cover;
  border-radius: 15px;
}

.news-item {
  overflow: hidden;
  border-radius: 10px;
}

.tibo {
  text-align: left;
}

.tibo .bold{
  font-weight: bold;
  font-size: 19px;
}

.tibo .bold2{
  font-size: 16px;
  padding-top: 5px;
  line-height: 30px;
}


@media (max-width: 768px) {
  .news{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }

  .news-item .img-news {
    width: 300px;
    height: 200px;
    object-fit: cover;
    border-radius: 10px;
  }

  .tibo {
    text-align: left;
  }

  .tibo .bold{
    font-weight: bold;
    font-size: 15px;
  }

  .tibo .bold2{
    font-size: 12px;
    padding-top: 8px;
  }
}

@media (max-width: 480px) {
  .news-item .img-news {
    width: 360px;
    height: 190px;
    object-fit: cover;
    border-radius: 10px;
  }

  .tibo {
    text-align: left;
  }

  .tibo .bold{
    font-weight: bold;
    font-size: 14.3px;
  }

  .tibo .bold2{
    font-size: 14px;
  }
}


</style>
