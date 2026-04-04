import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import '../src/assets/style.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faCoffee, faPhone, faMapMarkerAlt , faLocationPin  } from '@fortawesome/free-solid-svg-icons'; 
import { faFacebook, faTwitter, faInstagram  } from '@fortawesome/free-brands-svg-icons'; 
import { faEnvelope } from '@fortawesome/free-regular-svg-icons';
import { faPinterest } from '@fortawesome/free-brands-svg-icons';


library.add(faCoffee, faPhone, faEnvelope, faMapMarkerAlt, faFacebook, faTwitter, faInstagram , faLocationPin , faPinterest);

const app = createApp(App);
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(store).use(router);
app.mount('#app');
