 <template>
   <div>
      <div id="mapecharts"  ref="map"></div>

   </div>
 </template>
 <script>

import '@/common/maps/china.js'
import * as echarts from 'echarts';
export default {
    name:"china",
  data() {
    return {};
  },
  mounted() {
    this.$nextTick(() => {
      var nameColor = "rgb(30, 200, 230)"
      var name_fontFamily = '等线'
      var subname_fontSize = 15
      var name_fontSize = 25
      var mapName = 'china'
      var data1 = [
        { name: "北京", value: 16382.95 },
        { name: "天津", value: 1195.02 },
        { name: "河北", value: 188024.77 },
        { name: "山西", value: 156803.76 },
        { name: "内蒙古", value: 1143759.34 },
        { name: "辽宁", value: 147045.02 },
        { name: "吉林", value: 190667.68 },
        { name: "黑龙江", value: 452882.40 },
        { name: "上海", value: 7795.87 },
        { name: "江苏", value: 100701.53 },
        { name: "浙江", value: 106940.95 },
        { name: "安徽", value: 139950.72 },
        { name: "福建", value: 350 },
        { name: "江西", value: 492 },
        { name: "山东", value: 465 },
        { name: "河南", value: 584 },
        { name: "湖北", value: 658 },
        { name: "湖南", value: 726 },
        { name: "重庆", value: 286 },
        { name: "四川", value: 1311 },
        { name: "贵州", value: 457 },
        { name: "云南", value: 721 },
        { name: "西藏", value: 2412 },
        { name: "陕西", value: 532 },
        { name: "甘肃", value: 1174 },
        { name: "青海", value: 1821 },
        { name: "宁夏", value: 231 },
        { name: "新疆", value: 2883 },
        { name: "广东", value: 509 },
        { name: "广西", value: 573 },
        { name: "海南", value: 177 },
        { name: "台湾", value: 156 }
      ];
      var data = [
        { name: "北京", value: 16382.95 },
        { name: "天津", value: 1195.02 },
        { name: "河北", value: 188024.77 },
        { name: "山西", value: 156803.76 },
        { name: "内蒙古", value: 1143759.34 },
        { name: "辽宁", value: 147045.02 },
        { name: "吉林", value: 190667.68 },
        { name: "黑龙江", value: 452882.40 },
        { name: "上海", value: 7795.87 },
        { name: "江苏", value: 100701.53 },
        { name: "浙江", value: 106940.95 },
        { name: "安徽", value: 139950.72 },
        { name: "福建", value: 124720.98 },
        { name: "江西", value: 167243.31 },
        { name: "山东", value: 157169.01 },
        { name: "河南", value: 165762.59 },
        { name: "湖北", value: 186197.84 },
        { name: "湖南", value: 212338.98 },
        { name: "重庆", value: 82514.09 },
        { name: "四川", value: 486898.56 },
        { name: "贵州", value: 176472.11 },
        { name: "云南", value: 384040.31 },
        { name: "西藏", value: 1190899.90 },
        { name: "陕西", value: 205687.23 },
        { name: "甘肃", value: 425856.55 },
        { name: "青海", value: 693345.35 },
        { name: "宁夏", value: 51905.75 },
        { name: "新疆", value: 1628528.31 },
        { name: "广东", value: 181487.15 },
        { name: "广西", value: 237974.31 },
        { name: "海南", value: 40065.62 },
        { name: "台湾", value: 38186.37 }
      ];
      var geoCoordMap = {};
      var toolTipData = [
        {
          name: "北京",
          value: [{ name: "覆盖面积", value: 16382.95 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "天津",
          value: [{ name: "覆盖面积", value: 1195.02 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "河北",
          value: [{ name: "覆盖面积", value: 188024.77 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "山西",
          value: [{ name: "覆盖面积", value: 156803.76 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "内蒙古",
          value: [{ name: "覆盖面积", value: 1143759.34 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "辽宁",
          value: [{ name: "覆盖面积", value: 147045.02 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "吉林",
          value: [{ name: "覆盖面积", value: 190667.68 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "黑龙江",
          value: [{ name: "覆盖面积", value: 452882.40 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "上海",
          value: [{ name: "覆盖面积", value: 7795.87     }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "江苏",
          value: [{ name: "覆盖面积", value: 100701.53 }, { name: "覆盖率", value: 97.66 }]
        },
        {
          name: "浙江",
          value: [{ name: "覆盖面积", value: 106940.95 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "安徽",
          value: [{ name: "覆盖面积", value: 139950.72 }, { name: "覆盖率", value: 99.72 }]
        },
        {
          name: "福建",
          value: [{ name: "覆盖面积", value: 124720.98 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "江西",
          value: [{ name: "覆盖面积", value: 167243.31 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "山东",
          value: [{ name: "覆盖面积", value: 157169.01 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "河南",
          value: [{ name: "覆盖面积", value: 165762.59 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "湖北",
          value: [{ name: "覆盖面积", value: 186197.84 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "湖南",
          value: [{ name: "覆盖面积", value: 212338.98 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "重庆",
          value: [{ name: "覆盖面积", value: 82514.09 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "四川",
          value: [{ name: "覆盖面积", value: 486898.56 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "贵州",
          value: [{ name: "覆盖面积", value: 176472.11 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "云南",
          value: [{ name: "覆盖面积", value: 384040.31 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "西藏",
          value: [{ name: "覆盖面积", value: 1190899.90 }, { name: "覆盖率", value: 98.97 }]
        },
        {
          name: "陕西",
          value: [{ name: "覆盖面积", value: 205687.23 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "甘肃",
          value: [{ name: "覆盖面积", value: 425856.55 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "青海",
          value: [{ name: "覆盖面积", value: 693345.35 }, { name: "覆盖率", value: 99.50 }]
        },
        {
          name: "宁夏",
          value: [{ name: "覆盖面积", value: 51905.75 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "新疆",
          value: [{ name: "覆盖面积", value: 1628528.31 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "广东",
          value: [{ name: "覆盖面积", value: 181487.15 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "广西",
          value: [{ name: "覆盖面积", value: 237974.31 }, { name: "覆盖率", value: 100.00 }]
        },
        {
          name: "海南",
          value: [{ name: "覆盖面积", value: 40065.62 }, { name: "覆盖率", value: 91.92 }]
          },
        {
          name: "台湾",
          value: [{ name: "覆盖面积", value: 38186.37 }, { name: "覆盖率", value: 100.00 }]
        }
      ];

      /*获取地图数据*/
      let myChart = echarts.init(document.getElementById("mapecharts"));
      // myChart.showLoading();
      var mapFeatures = echarts.getMap(mapName).geoJson.features;
      // myChart.hideLoading();
      mapFeatures.forEach(function(v) {
        // 地区名称
        var name = v.properties.name;
        // 地区经纬度
        geoCoordMap[name] = v.properties.cp;
      });
      // console.log(data);
      // console.log(toolTipData);
      var max = 100,min = 9; // todo
      var maxSize4Pin = 50,minSize4Pin = 20;

      var convertData = function(data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value),
            });
            // console.log(geoCoord.concat(data[i].value)+"222222222222")
        }
          }
    // console.log(res+"111111111")
    return res;
        };
console.log(convertData(data)+  "9999999999999")
        var option = {
            title: {
                // text: name_title,
                // subtext: subname,
                x: 'center',
                textStyle: {
                    color: nameColor,
                    fontFamily: name_fontFamily,
                    fontSize: name_fontSize
                },
            },
        tooltip: {
          trigger: "item",
          formatter: function(params) {
            if (typeof params.value[2] == "undefined") {
              var toolTiphtml = "";
              for (var i = 0; i < toolTipData.length; i++) {
                if (params.name == toolTipData[i].name) {
                  toolTiphtml += toolTipData[i].name + ":<br>";
                  for (var j = 0; j < toolTipData[i].value.length; j++) {
                    toolTiphtml +=
                      toolTipData[i].value[j].name +
                      ":" +
                      toolTipData[i].value[j].value +
                      "<br>";
                  }
                }
              }
               console.log(toolTiphtml+"33333333333");
              console.log(convertData(data))
              return toolTiphtml;
            } else {
              var toolTiphtml = "";
              for (var i = 0; i < toolTipData.length; i++) {
                if (params.name == toolTipData[i].name) {
                  toolTiphtml += toolTipData[i].name + ":<br>";
                  for (var j = 0; j < toolTipData[i].value.length; j++) {
                    toolTiphtml +=
                      toolTipData[i].value[j].name +
                      ":" +
                      toolTipData[i].value[j].value + 
                      "<br>";
                  }
                }
              }
              console.log(toolTiphtml);
              // console.log(convertData(data))
              return toolTiphtml;
            }
          }
        },
        //这个属性控制地图的多彩板块
        visualMap: {
          show: true,
          min: 0,
          max: 100000,
          left: 'left',
          bottom: 'bottom',
          text: ["高", "低"], // 文本，默认为数值文本
          calculable: true,
          seriesIndex: [1],
          inRange: {
            // color: ['#3B5077', '#031525'] // 蓝黑
            // color: ['#ffc0cb', '#800080'] // 红紫
            // color: ['#3C3B3F', '#605C3C'] // 黑绿
            // color: ['#0f0c29', '#302b63', '#24243e'] // 黑紫黑
            // color: ['#23074d', '#cc5333'] // 紫红
            //color: ["#00467F", "#A5CC82"] // 蓝绿
            // color: ['#1488CC', '#2B32B2'] // 浅蓝
            // color: ['#00467F', '#A5CC82'] // 蓝绿
            // color: ['#00467F', '#A5CC82'] // 蓝绿
             color: ['rgba(69, 245, 104, 0.9)','#d7ba7d','rgba(69,117,245, .9)' ]//取值范围的颜色
            // color: ['#00467F', '#A5CC82'] // 蓝绿
          }
        },
        geo: {
          show: true,
            map: mapName,
          zoom:1.23,//视角缩放比例
          label: {
            normal: {
              show: false
            },
            emphasis: {
              show: false
            }
          },
          roam: true,
          itemStyle: {
            normal: {
              areaColor: "#031525",
              borderColor: "#3B5077"
            },
            emphasis: {
              areaColor: "#2B91B7"
            }
          }
        },
        series: [
        //1）全省的散点
          {
            name: "散点",
            type: "scatter",
            coordinateSystem: "geo",
            data: convertData(data),
             
            symbolSize: function (val) {
                // console.log(val+"4444444444")
              return val[2] / 18000;
                },
            
            label: {
              normal: {
                formatter: "{b}",
                position: "right",
                show: true
              },
              emphasis: {
                show: true
              }
            },
            itemStyle: {
              normal: {
                color: "#05C3F9"
              }
            }
          },
          // 2）为了与visualMap配对，必须要加上
          {
            type: "map",
            map: mapName,
            geoIndex: 0,
            aspectScale: 0.80, //长宽比
            showLegendSymbol: true, // 存在legend时显示
            label: {
              normal: {
                show: true
              },
              emphasis: {
                show: false,
                textStyle: {
                    color: "rgb(236, 24, 13)"
                }
              }
            },
            roam: true,
            itemStyle: {
              normal: {
                areaColor: "#031525",
                // borderColor: "#3B5077"
              },
              emphasis: {
                areaColor: "#2B91B7"
              }
            },
            animation: false,
            data: data
           
          },
          
          //3）气泡
          // {
          //   name: "点",
          //   type: "scatter",
          //   coordinateSystem: "geo",
          //   symbol: "pin", //气泡
          //   symbolSize: function(val) {
          //   //   var a = maxSize4Pin / (max - min) / 400;
          //   //   var b = minSize4Pin - a * min / 100;
          //   //   b = maxSize4Pin - a * max / 10;
          //     return  val[1]/10000 ;
          //   },
          //   label: {
          //     normal: {
          //       show: true,
          //       textStyle: {
          //         color: "#fff",
          //         fontSize: 10
          //       }
          //     }
          //   },
          //   itemStyle: {
          //     normal: {
          //       color: "#F62157" //标志颜色
          //     }
          //   },
          //     zlevel: 7,
            
          //   data: data.value
          //   },
          //4.前五个有效果的光圈
          {
            name: "Top 5",
            type: "effectScatter",
            coordinateSystem: "geo",
            data: convertData(
              data
                .sort(function(a, b) {
                  return b.value - a.value;
                })
                    .slice(0, 5),
            
            ),
            symbolSize: function(val) {
              return val[2] / 1500000;
            },
            showEffectOn: "render",
            rippleEffect: {
              brushType: "stroke"
            },
            hoverAnimation: true,
            label: {
              normal: {
                formatter: "{b}",
                position: "right",
                show: true
              }
            },
            itemStyle: {
              normal: {
                color: "yellow",
                shadowBlur: 10,
                shadowColor: "yellow"
              }
            },
            zlevel: 1
          }
        ]
      };
      myChart.setOption(option);
    });
  }
};
</script>
<style>
#mapecharts{
    /* color: #2B91B7; */
    width: 900px;
    height: 610px;
    color:rgba(69, 245, 104, 0.9);
}
</style>
 

