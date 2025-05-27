<template>
  <div id="footleft" ref="chartsfoot" ></div>
</template>

<script>
import * as echarts from 'echarts';
export default {
name: "footleft",
    data() {
        return {}
    },
    methods: {
        initchart() {
            var category = ['GF1', 'GF2', 'GF3', 'GF4', 'GF5', 'GF6', 'GF7'];
            var dottedBase = [];
            var lineData = [266, 173, 728, 165, 151, 132, 96];
            var barData = [2372752, 2301432, 444740, 784290, 981626, 1766709, 608244];
            let Chart = echarts.init(document.getElementById('footleft'));
            let option = {
                title: {
                    text: '高分系列卫星归档数据',
                    x: 'center',
                    y: 0,
                    textStyle: {
                        color: '#B4B4B4',
                        fontSize: 16,
                        fontWeight: 'normal',
                    },

                },
               backgroundColor: '#031d33',
                tooltip: {
                    trigger: 'axis',
                    backgroundColor: 'rgba(255,255,255,0.1)',
                    axisPointer: {
                        type: 'shadow',
                        label: {
                            show: true,
                            backgroundColor: '#7B7DDC'
                        }
                    }
                },
                legend: {
                    data: ['增值税应纳税额', '一般纳税人户数', '增幅',],
                    textStyle: {
                        color: '#B4B4B4'
                    },
                    top: '7%',
                },
                grid: {
                    x: '12%',
                    width: '82%',
                    y: '12%',
                },
                xAxis: {
                    data: category,
                    axisLine: {
                        lineStyle: {
                            color: '#B4B4B4'
                        }
                    },
                    axisTick: {
                        show: false,
                    },
                },
                yAxis: [{

                    splitLine: { show: false },
                    axisLine: {
                        lineStyle: {
                            color: '#B4B4B4',
                        }
                    },

                    axisLabel: {
                        formatter: '{value} ',
                    }
                },
                {

                    splitLine: { show: false },
                    axisLine: {
                        lineStyle: {
                            color: '#B4B4B4',
                        }
                    },
                    axisLabel: {
                        formatter: '{value} ',
                    }
                }],

                series: [{
                    name: '24小时内增加',
                    type: 'line',
                    smooth: true,
                    showAllSymbol: true,
                    symbol: 'emptyCircle',
                    symbolSize: 8,
                    yAxisIndex: 1,
                    itemStyle: {
                        normal: {
                            color: '#F02FC2'
                        },
                    },
                    data: lineData
                },

                {
                    name: '历史归档产品',
                    type: 'bar',
                    barWidth: 10,
                    itemStyle: {
                        normal: {
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    { offset: 0, color: '#956FD4' },
                                    { offset: 1, color: '#3EACE5' }
                                ]
                            )
                        }
                    },
                    data: barData
                }
                ]
            };
            Chart.setOption(option)
        },
       
    },
    mounted() {
        this.initchart();
    }
}
</script>

<style>
#footleft{
width: 600px;
  height: 300px;
  margin-top: 5px;
}
</style>