<template>
  <canvas ref="canvas" class="particles-bg"></canvas>
</template>

<script setup>
import { onMounted, onBeforeUnmount, ref } from 'vue';

const canvas = ref(null);
let particles = [];
let animationId = null;

const init = () => {
  const ctx = canvas.value.getContext('2d');
  canvas.value.width = window.innerWidth;
  canvas.value.height = window.innerHeight;

  // 创建粒子
  particles = Array.from({ length: 80 }, () => ({
    x: Math.random() * canvas.value.width,
    y: Math.random() * canvas.value.height,
    size: Math.random() * 3 + 1,
    speedX: Math.random() * 2 - 1,
    speedY: Math.random() * 2 - 1
  }));

  const animate = () => {
    ctx.clearRect(0, 0, canvas.value.width, canvas.value.height);

    particles.forEach(p => {
      p.x += p.speedX;
      p.y += p.speedY;

      // 边界检查
      if (p.x < 0 || p.x > canvas.value.width) p.speedX *= -1;
      if (p.y < 0 || p.y > canvas.value.height) p.speedY *= -1;

      // 绘制粒子
      ctx.fillStyle = 'rgba(255, 255, 255, 0.5)';
      ctx.beginPath();
      ctx.arc(p.x, p.y, p.size, 0, Math.PI * 2);
      ctx.fill();
    });

    animationId = requestAnimationFrame(animate);
  };

  animate();
};

onMounted(() => {
  init();
  window.addEventListener('resize', init);
});

onBeforeUnmount(() => {
  cancelAnimationFrame(animationId);
  window.removeEventListener('resize', init);
});
</script>

<style scoped>
.particles-bg {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  pointer-events: none;
}
</style>