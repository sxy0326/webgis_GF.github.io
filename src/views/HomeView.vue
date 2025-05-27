<template>
<div class="home">
      <Header></Header>
      <div class="topleft">
        <dv-border-box-8>
          <TopLeft></TopLeft>
        </dv-border-box-8>
      </div>
      <div class="footleft">
        <dv-border-box-8>
          <FootLeft></FootLeft>
        </dv-border-box-8>
      </div>
      <div class="topcenter">
        <dv-border-box-12>
          <Map></Map>
        </dv-border-box-12>
      </div>

</div>
</template>

<script>
import Header from '../components/Header.vue'
import TopLeft from '../components/TopLeft.vue'
import FootLeft from '../components/FootLeft.vue'
import Map from '../components/Map.vue'

export default {
  name:"home",
  components: {
    Header,
    TopLeft,
    FootLeft,
    Map
}
}
</script>
<style scoped>
.topcenter{
  position: absolute;
  top: 78px;
  left: 620px;
  width: 900px;
  height: 610px;
  margin-left: 10px;
  margin-top: 5px;
}
.footleft{
  top: 410px;
  width: 600px;
  height: 300px;
  margin-top: 5px;
}
.topleft{
  top: 100px;
  width: 600px;
  height: 300px;
  margin-top: 5px;
}
.home{
    width: 100%;
    height: 100%;
    padding: 16px 16px 0 16px;
    top: 0px;
    left: 0px;
    bottom: 0px;
    right: 0px;
    position: fixed;
    z-index: -1;    
    background-image: url("../assets/pageBg.png");
    background-size: cover;
    background-position: center center;
}
</style>
