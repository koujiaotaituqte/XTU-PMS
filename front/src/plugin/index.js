import Vue  from "vue";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

//注册myplugin插件
import myPlugin from "./myPlugin";

Vue.use(myPlugin);