<template>
  <div class="hotel-container">
    <header>
      <h1>湘大海岸酒店</h1>
      <p class="subtitle">— 奢华体验，尽享海滨风情 —</p>
    </header>

    <section class="intro-section">
      <h2>酒店简介</h2>
      <p>湘大海岸酒店坐落于风景如画的滨海度假区，毗邻金色沙滩和碧蓝大海，为您提供无与伦比的度假体验。酒店融合现代设计与自然美景，拥有各类豪华客房、顶级餐饮服务和丰富娱乐设施，是商务出行与休闲度假的理想之选。</p>
    </section>

    <section class="features-section">
      <h2>特色亮点</h2>
      <ul class="features-list">
        <li>绝佳位置：步行3分钟直达私人海滩，尽享阳光与海浪</li>
        <li>豪华客房：海景房、套房等多种选择，配备智能家居系统</li>
        <li>美食盛宴：3家主题餐厅，提供国际美食和本地特色海鲜</li>
        <li>休闲设施：无边泳池、SPA水疗中心、24小时健身房</li>
        <li>会议服务：5个多功能厅，适合商务会议和浪漫婚礼</li>
      </ul>
    </section>

    <section class="rooms-section">
      <h2>房型推荐</h2>

      <div class="room-card" v-for="(room, index) in rooms" :key="index">
        <h3 class="room-title">{{ room.name }}</h3>
        <div class="room-details">
          <span v-for="(feature, idx) in room.features" :key="idx" class="room-feature">{{ feature }}</span>
        </div>
        <p class="room-description">{{ room.description }}</p>
      </div>
    </section>

    <section class="facilities-section">
      <h2>配套设施</h2>
      <div class="facilities-grid">
        <div class="facility-card" v-for="(facility, index) in facilities" :key="index">
          <div class="facility-icon">
            <i :class="facility.icon"></i>
          </div>
          <h3>{{ facility.title }}</h3>
          <p>{{ facility.description }}</p>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'HotelInfo',
  data() {
    return {
      rooms: [
        {
          name: '豪华海景大床房',
          features: ['面积：45㎡', '大床', '私人阳台', '海景浴缸'],
          description: '特色：180°全景落地窗，尽览日出日落。'
        },
        {
          name: '行政套房',
          features: ['面积：80㎡', '独立客厅', '迷你吧', '专属管家服务'],
          description: '特色：适合家庭或高端商务人士。'
        },
        {
          name: '花园别墅',
          features: ['面积：120㎡', '私人泳池', '露天庭院', '24小时客房服务'],
          description: '特色：隐秘性强，适合蜜月或独家度假。'
        }
      ],
      facilities: [
        {
          icon: 'fas fa-utensils',
          title: '餐饮',
          description: '海鲜自助餐厅、天空酒吧、中式私房菜'
        },
        {
          icon: 'fas fa-swimming-pool',
          title: '娱乐',
          description: '儿童俱乐部、水上活动中心、高尔夫球场（附近）'
        },
        {
          icon: 'fas fa-briefcase',
          title: '商务',
          description: '高速WiFi、会议室、商务中心'
        }
      ]
    }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.hotel-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Microsoft YaHei', sans-serif;
}

header {
  text-align: center;
  padding: 30px 0;
  background: linear-gradient(135deg, #1e5799, #2989d8);
  color: white;
  border-radius: 10px;
  margin-bottom: 30px;
  box-shadow: 0 5px 15px rgba(0,0,0,0.1);
}

h1 {
  font-size: 2.5rem;
  margin-bottom: 10px;
}

.subtitle {
  font-size: 1.2rem;
  font-weight: 300;
}

section {
  background: white;
  border-radius: 10px;
  padding: 30px;
  margin-bottom: 30px;
  box-shadow: 0 5px 15px rgba(0,0,0,0.05);
}

h2 {
  color: #2989d8;
  border-bottom: 2px solid #e0e0e0;
  padding-bottom: 10px;
  margin-bottom: 20px;
  font-size: 1.8rem;
}

p {
  margin-bottom: 15px;
  text-align: justify;
}

.features-list {
  list-style-type: none;
  margin: 20px 0;
}

.features-list li {
  margin-bottom: 10px;
  padding-left: 25px;
  position: relative;
  font-size: 1.1rem;
}

.features-list li:before {
  content: "✓";
  color: #2ecc71;
  font-weight: bold;
  position: absolute;
  left: 0;
}

.room-card {
  background: #f9f9f9;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 20px;
  transition: transform 0.3s ease;
}

.room-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.1);
}

.room-title {
  color: #2c3e50;
  margin-bottom: 10px;
  font-size: 1.3rem;
}

.room-details {
  display: flex;
  justify-content: space-between;
  background: #e6f7ff;
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 15px;
  flex-wrap: wrap;
}

.room-feature {
  font-size: 0.9rem;
  color: #555;
  margin-right: 15px;
  margin-bottom: 5px;
}

.room-description {
  background: #f0f8ff;
  padding: 15px;
  border-radius: 5px;
  font-style: italic;
  color: #555;
}

.facilities-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.facility-card {
  background: #f9f9f9;
  border-radius: 8px;
  padding: 20px;
  text-align: center;
  transition: transform 0.3s ease;
}

.facility-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0,0,0,0.1);
}

.facility-icon {
  font-size: 2rem;
  margin-bottom: 15px;
  color: #3498db;
}

@media (max-width: 768px) {
  .facilities-grid {
    grid-template-columns: 1fr;
  }

  .room-details {
    flex-direction: column;
  }

  .room-feature {
    margin-right: 0;
  }
}
</style>