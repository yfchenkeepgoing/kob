<template>
    <div ref="parent" class="gamemap">
        <canvas ref="canvas"></canvas>
    </div>
</template>

<script>
// 创建游戏对象：游戏地图
import { GameMap } from '../assets/scripts/GameMap';
// 引用canvas
import { ref, onMounted } from 'vue'

export default {
    setup() {
        // 将二者与<template>中的div和canvas产生关联
        let parent = ref(null); // 没有指向任何元素
        let canvas = ref(null); 

        // 组件挂载完后需要创建游戏对象
        onMounted(() => {
            new GameMap(canvas.value.getContext('2d'), parent.value)
        });

        return {
            parent,
            canvas
        }
    }
}
</script>

<style scoped>
div.gamemap{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>