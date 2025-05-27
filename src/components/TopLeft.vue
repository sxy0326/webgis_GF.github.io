<template>
  <div id="topleft" ref="charts" ></div>
</template>

<script>
import * as echarts from 'echarts';
export default {
    name: "topleft",
    data() {
        return {
        
        
        }
      
    },
    
    methods: {
    mycharts(){
    let myChart = echarts.init(document.getElementById('topleft'));
    let option = {
  backgroundColor: '#031d33',
  legend: {
    top: '20',
    x: 'center',
    textStyle: {
      fontSize: 16,
      color: 'rgba(101, 213, 255, 1)',
    },
    icon:
      'path://M512 881.777778 512 881.777778C716.222629 881.777778 881.777778 716.222629 881.777778 512 881.777778 307.777371 716.222629 142.222222 512 142.222222 307.777373 142.222222 142.222222 307.777371 142.222222 512 142.222222 716.222629 307.777373 881.777778 512 881.777778L512 881.777778ZM512 1024 512 1024C229.230208 1024 0 794.769789 0 512 0 229.230211 229.230208 0 512 0 794.769789 0 1024 229.230211 1024 512 1024 794.769789 794.769789 1024 512 1024L512 1024Z',
    itemWidth: 8, // 设置宽度
    itemHeight: 8, // 设置高度、
    itemGap: 12, // 设置间距
  },
  tooltip: {
    show: true,
    trigger: 'axis', //axis , item
    backgroundColor: 'RGBA(0, 49, 85, 1)',
    borderColor: 'rgba(0, 151, 251, 1)',
    borderWidth: 1,
    borderRadius: 0,
    textStyle: {
      color: '#BCE9FC',
      fontSize: 16,
      align: 'left',
    },
  },
  grid: {
    right: '5%',
    top: '10%',
    left: '5%',
    bottom: '5%',
    containLabel: true,
  },
  xAxis: {
    name: 'KM2',
    nameTextStyle: {
      color: '#65d5ff',
    },
    type: 'category',
    boundaryGap: true,
    data: ['上海', '重庆', '广西', '北京', '陕西', '山西', '天津', '河北', '贵州', '香港','山东','宁夏','安徽','河南','湖南','澳门','四川','内蒙','辽宁','江苏','湖北','江西','黑龙','广东','西藏','新疆','福建','甘肃','吉林','浙江','青海','海南','云南','台湾'],
    axisLabel: {
      //坐标轴刻度标签的相关设置。
      interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
      //	margin:15,
      textStyle: {
        color: '#65D5FF',
        fontStyle: 'normal',
        fontSize: 12,
      },
    },
    axisTick: {
      //坐标轴刻度相关设置。
      show: false,
    },
    axisLine: {
      //坐标轴轴线相关设置
      lineStyle: {
        color: 'rgba(77, 128, 254, 0.2)',
      },
    },
    splitLine: {
      //坐标轴在 grid 区域中的分隔线。
      show: true,
      lineStyle: {
        color: 'rgba(77, 128, 254, 0.2)',
      },
    },
  },
  dataZoom: [
    {
      type: 'slider',
      show: true,
      bottom: '20px',
      borderColor: '#07417a',
      backgroundColor: 'transparent',
      dataBackground: {
        lineStyle: {
          color: 'transparent',
          shadowOffsetY: 0,
        },
        areaStyle: {
          color: 'transparent',
          shadowOffsetY: 0,
        },
      },
      // 拖拽手柄样式 svg 路径
      handleIcon:
        'M512 512m-208 0a6.5 6.5 0 1 0 416 0 6.5 6.5 0 1 0-416 0Z M512 192C335.264 192 192 335.264 192 512c0 176.736 143.264 320 320 320s320-143.264 320-320C832 335.264 688.736 192 512 192zM512 800c-159.072 0-288-128.928-288-288 0-159.072 128.928-288 288-288s288 128.928 288 288C800 671.072 671.072 800 512 800z',
      handleColor: '#aab6c6',
      height: 6,
      handleSize: 12,
      showDataShadow: false,
      filterMode: 'filter',
      textStyle: {
        color: '#ccc',
      },
      start: 0,
      end: 35,
    },
  ],
  yAxis: [
    {
      name: '景',
      nameTextStyle: {
        color: '#65d5ff',
      },
      type: 'value',
      splitNumber: 3,
      axisLabel: {
        textStyle: {
          color: '#65D5FF',
          fontStyle: 'normal',
          fontSize: 12,
        },
      },
      axisLine: {
        show: false,
      },
      axisTick: {
        show: false,
      },
      splitLine: {
        show: true,
        lineStyle: {
          color: 'rgba(77, 128, 254, 0.2)',
        },
      },
    },
    {
      name: '',
      nameTextStyle: {
        color: '#65d5ff'
      },
      min: 0,
      splitLine: {
        show: true,
        lineStyle: {
          color: '#233653'
        }
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#233653'
        }
      },
      axisLabel: {
        show: true,
        textStyle: {
          color: '#78bdf5'
        },
      },
      axisTick: {
        show: false
      }
    }
  ],
  series: [
    {
      name: '云覆盖量30%',
      type: 'pictorialBar',
      barWidth: '50%',
      label: {
        normal: {
          show: false,
        },
      },
      itemStyle: {
        normal: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [
              {
                offset: 0,
                color: 'rgba(255, 64, 0, 0.8)', // 0% 处的颜色
              },
              {
                offset: 1,
                color: 'rgba(0, 34, 66, 0.2)', // 100% 处的颜色
              },
            ],
            globalCoord: false, // 缺省为 false
          }, //渐变颜色
        },
      },
      symbol:
        'path://M12.000,-0.000 C12.000,-0.000 16.074,60.121 22.731,60.121 C26.173,60.121 -3.234,60.121 0.511,60.121 C7.072,60.121 12.000,-0.000 12.000,-0.000 Z',

      data: [42, 228, 421, 74, 447, 456, 41, 500, 331, 21,393,190,401,532,623,5,958,2411,432,282,596,443,1074,395,1514,2965,305,915,465,281,1347,160,403,130],

    },
    {
      name: '云覆盖量100%',
      type: 'pictorialBar',
      barWidth: '50%',
      label: {
        normal: {
          show: false,
        },
      },
      itemStyle: {
        normal: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [
              {
                offset: 0,
                color: 'rgba(48, 236, 166,0.8)', // 0% 处的颜色
              },
              {
                offset: 1,
                color: 'rgba(0, 34, 66, 0.2)', // 100% 处的颜色
              },
            ],
            globalCoord: false, // 缺省为 false
          }, //渐变颜色
        },
      },
      symbol:
        'path://M12.000,-0.000 C12.000,-0.000 16.074,60.121 22.731,60.121 C26.173,60.121 -3.234,60.121 0.511,60.121 C7.072,60.121 12.000,-0.000 12.000,-0.000 Z',

      data: [57,286,573,89,532,481,51,575,457,21,465,231,441,584,726,7,1311,2883,584,337,658,492,143,305,3863,2412,350,1174,647,315,1821,177,492,156],
    },
    {
      name: '100%覆盖面积',
      type: 'line',
      data: [7795.87, 82514.09, 237974.31, 16382.95, 205687.23, 156803.76, 11950.02, 188024.77, 176472.11, 1269.98,157169.01,51905.75,139950.65,165762.59,212338.98,49.51,486898.56,1143759.34,147045.02,100701.53,186197.84,167243.31,452882.40,181487.15,1190899.90,1190899.90,1628528.31,124720.98,425856.55,190667.88,106940.95,693345.35,40065.52,384040.31,38186.37],
      smooth: true,
      // symbol: 'none',
      // barWidth: '50%',
      label: {
        normal: {
          show: false,
        },
      },
        lineStyle: {
        normal: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
            offset: 0,
            color: 'rgba(255, 227, 168, 0.3)'
          }, {
            offset: 0.5,
            color: 'rgba(255, 227, 168, 1)'
          }, {
            offset: 1,
            color: 'rgba(255, 227, 168, 0.3)'
          }]),
          shadowColor: 'rgba(255, 120, 0,1)',
          shadowBlur: 10
        }
      },
      yAxisIndex: 1,
      symbol:
        'path://M12.000,-0.000 C12.000,-0.000 16.074,60.121 22.731,60.121 C26.173,60.121 -3.234,60.121 0.511,60.121 C7.072,60.121 12.000,-0.000 12.000,-0.000 Z',

      
    },
     {
      name: '30%覆盖面积',
      type: 'line',
      data: [7701.77,82514.09,237916.34,16382.95,205687.23,156803.76,11950.02,188024.77,176472.11,1269.98,157017.30,51905.75,139950.65,165762.59,212338.98,49.51,485990.59,1143759.34,147045.02,100234.69,186197.84,167243.31,452882.40,179241.54,1174801.35,1858020.25,124720.98,424321.21,190667.88,106694.69,664407.85,39933.86,384040.31,38186.37],
      smooth: true,
      symbol: 'none',
      lineStyle: {
        normal: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
            offset: 0,
            color: 'rgba(255, 227, 168, 0.3)'
          }, {
            offset: 0.5,
            color: 'rgba(255, 227, 168, 1)'
          }, {
            offset: 1,
            color: 'rgba(255, 227, 168, 0.3)'
          }]),
          shadowColor: 'rgba(255, 120, 0,1)',
          shadowBlur: 10
        }
      },
      yAxisIndex: 1
    },
  ],
};


         myChart.setOption(option)
         //图表自适应
         window.addEventListener("resize",function(){
         myChart.resize() // myChart 是实例对象
         })
     }
    },
    mounted() {
         this.mycharts()
        var myChart = document.getElementById("topleft");
        //  myChart.setOption(this.option)

    
}
}
         
</script>

<style>
#topleft{
    width: 600px;
    height: 300px;
}
</style>