import {get,post} from '../util/request'


//定义插件
export default{
    //插件必须包含一个install
    install:function(Vue){
        Vue.mixin({
            methods: {
                $get(url,params){
                    return get(url,params)
                },
                $post(url,params){
                    return post(url,params)
                }
            }
        })
    }
}