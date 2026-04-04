<!-- <template>
  <div>
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
      <path
        fill="#36363e"
        fill-opacity="1"
        d="M0,96L40,117.3C80,139,160,181,240,186.7C320,192,400,160,480,149.3C560,139,640,149,720,176C800,203,880,245,960,250.7C1040,256,1120,224,1200,229.3C1280,235,1360,277,1400,298.7L1440,320L1440,320L1400,320C1360,320,1280,320,1200,320C1120,320,1040,320,960,320C880,320,800,320,720,320C640,320,560,320,480,320C400,320,320,320,240,320C160,320,80,320,40,320L0,320Z"
      ></path>
    </svg>
    <footer>
      <div class="container-footer">
        <div class="footer-content">
          <h3>
            <a href="index.html" class="custom-link mb-1">Moso Interior</a>
          </h3>
          <p class="text-white">Since 1986, We crafted interior</p>
          <p class="text-white">products for better spaces</p>
          <p class="text-white">
            <a href="https://www.tooplate.com" target="_parent"
              >Web Design: Tooplate</a
            >
          </p>
        </div>

        <div class="footer-address">
          <h3 class="text-white mb-3">Store</h3>
          <p class="text-white mt-2">
            <font-awesome-icon :icon="['fas', 'location-pin']" />
            Berlin, Germany
          </p>
        </div>

        <div class="footer-contact">
          <h3 class="text-white mb-3">Contact Info</h3>
          <p class="text-white mb-1">
            <font-awesome-icon :icon="['fas', 'phone']" />
            <a href="tel: 090-080-0760" class="text-white"> 090-080-0760 </a>
          </p>
          <p class="text-white mb-0">
            <font-awesome-icon :icon="['far', 'envelope']" />
            <a href="mailto:info@company.com" class="text-white">
              info@company.com
            </a>
          </p>
        </div>
      </div>
      <div class="directory-footer">
        <p class="copyright-text mb-0 me-4">Copyright © Moso Interior 2048</p>
        <ul class="social-icon">
          <li class="social-icon-item">
            <a href="#"><font-awesome-icon :icon="['fab', 'twitter']" class="icon twitter-icon" /></a>
          </li>

          <li class="social-icon-item">
            <a href="#"><font-awesome-icon :icon="['fab', 'facebook']" class="icon facebook-icon" /></a>
          </li>

          <li class="social-icon-item">
            <a href="#"><font-awesome-icon :icon="['fab', 'instagram']" class="icon instagram-icon" /></a>
          </li>

          <li class="social-icon-item">
            <a href="#"><font-awesome-icon :icon="['fab', 'pinterest']" class="icon pinterest-icon" /></a>
          </li>

          <li class="social-icon-item">
            <a href="#"><font-awesome-icon :icon="['fas', 'phone']" class="icon phone-icon" /></a>
          </li>
        </ul>
      </div>
    </footer>
  </div>
</template>
<script>
export default {
  name: "FooterComponent",
};
</script>
<style scoped>
footer {
  background-color: #36363e;
  color: white;
  text-align: center;
  margin-top: -5px;
}

.container-footer {
  display: flex;
  justify-content: space-between;
  padding: 20px 100px;
  height: 276px;
}

.footer-content {
  text-align: left;
}

.footer-content h3 {
  margin-bottom: 25px;
}

.footer-content h3 a {
  font-size: 38px;
  color: #fff;
  position: relative;
  text-decoration: none;
  transition: color 0.3s ease;
}

.footer-content h3 a::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  width: 0;
  height: 2px;
  background-color: #48a45d;
  transition: width 0.4s ease;
}

.footer-content h3 a:hover {
  color: #ffd700;
}

.footer-content h3 a:hover::after {
  width: 100%;
}

.footer-content p {
  margin-bottom: 14px;
  color: #fff;
  font-size: 20px;
  font-weight: 300;
}

.footer-content p a {
  color: white;
  opacity: 0.5;
  font-weight: 200;
}

.footer-content p a:hover {
  color: #ff4d4d !important;
  text-shadow: 0 0 10px rgba(255, 77, 77, 1);
  font-weight: bold;
  transition: color 0.3s ease, text-shadow 0.3s ease;
}

.footer-address {
  text-align: left;
}

.footer-address h3 {
  font-size: 38px;
  color: #fff;
  font-weight: 600;
}

.footer-address p {
  margin-top: 14px;
  color: #fff;
  font-size: 20px;
  font-weight: 300;
}

.text-white p {
  display: flex;
  justify-content: center;
  align-items: center;
}

.footer-contact {
  text-align: left;
}

.footer-contact h3 {
  font-size: 38px;
  color: #fff;
  font-weight: 600;
}

.footer-contact p {
  margin-top: 15px;
}

.footer-contact p a {
  color: white;
  font-weight: 300;
  font-size: 20px;
}

.social-icon{
  display: flex;
  justify-content: center;
  align-items: center;
  list-style: none;
  margin-left: 20px;
}

.directory-footer p {
  opacity: 0.7;
}

.directory-footer {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-bottom: 100px;
}

.social-icon-item {
  padding-right: 30px;
  font-size: 23px;
  transition: color 0.3s ease;
}

.social-icon-item:nth-child(1) a {
  color: #1da1f2;
}

.social-icon-item:nth-child(2) a {
  color: #1877f2; 
}

.social-icon-item:nth-child(3) a {
  color: #c13584; 
}

.social-icon-item:nth-child(4) a {
  color: #e60023; 
}

.social-icon-item:nth-child(5) a {
  color: #128c7e;
}

.social-icon-item a:hover {
  color: #ffffff;
  transition: color 0.3s ease;
}

a {
  text-decoration: none;
}

@media (max-width: 768px) {
  .container-footer {
    flex-direction: column; 
    align-items: center; 
    padding: 20px; 
    height: 480px;
  }

  .footer-content,
  .footer-address,
  .footer-contact {
    text-align: center; 
    margin-bottom: 20px;
  }

  .footer-content h3 a,
  .footer-address h3,
  .footer-contact h3 {
    font-size: 25px; 
  }

  .footer-content p,
  .footer-address p,
  .footer-contact p {
    font-size: 17px; 
  }

  .directory-footer {
    flex-direction: column; 
    padding-bottom: 20px;
  }

  .social-icon {
    margin: 20px 0 0 0; 
  }

  .social-icon-item {
    padding-right: 15px; 
    font-size: 20px;
  }
}


</style> -->