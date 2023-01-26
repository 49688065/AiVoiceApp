package com.imooic.lib_network.bean

/**
{
"name":"双鱼座",
"date":"2022年",
"year":2022,
"mima":{
"info":"需要多多倾诉，不妨去影院看几场电影吧。",
"text":[
"双鱼座们今年的运势大体而言不错。由于水星和木星的影响，你们在新的一年里工作学习压力会略微有些增大，大家可以找个值得信任的人倾诉一番，你们需要靠倾诉来缓解自己内心的压力。当然，见人就说个不停也是不合适的，和对的人倾诉才能够真正缓解压力，改变自己的心情。双鱼座2022年可佩戴一个波塞冬海王星双环扣吉宏项链作为全年的幸运护身符饰物；项链由两个纯银的平安扣相互紧扣而成，项链上有“Poseidon”字母，代表双鱼座的守护神海神波塞冬，环扣上由双鱼座符号和守护星“海王星”图案组成，一环扣一环的双环平安扣紧密相依，象征着坚韧不催的毅力和勇气，激励和守护着双鱼们在2022年里勇往直前、顺风顺水。"
]
},
"career":[
"双鱼们今年的工作态度一直都是兢兢业业的，他们会把自己手头上面的任务认认真真的完成，不会有一丝一毫的懈怠。如此认真的态度自然是能够把事业学业搞得风生水起，不过凡事都需要一个过程，还需要再耐心去坚持一段时间才能见到成效。"
],
"love":[
"今年双鱼们的爱情运势有些低迷，单身的小伙伴们暂时不会有想要找对象的想法，所以就算缘分摆在面前，他们也难以主动把握住。已经有伴的双鱼们则需要增加自己与对方的共同话题，以免产生无聊、苦闷的心情。"
],
"health":[
"部分双鱼座会出现牙龈肿痛、口腔溃疡等口腔问题，大家一定要多吃一些泻火清热的蔬菜水果，以免上火的问题。"
],
"finance":[
"随着双鱼们收入的回升，很多小伙伴就会开始产生存钱的想法了，他们会把手头上多余的金钱的一部分先存下来当做积蓄，以防之后的不时之需。如此一来不但稳妥许多，心里也会比较有安全感。双鱼座今年可佩戴一串多宝石鸢尾花宝懿手链来提升金钱指数，此手链由多种财富宝石构成；其中男款宝石为黄虎眼、蓝砂石、月光石、黑曜石、地图石等组合，女款宝石则为七彩玉髓组成；而鸢尾花乃古希腊象征财富与尊贵的幸运物，寓意双鱼们在2022年财气满满，步步高升。"
],
"luckeyStone":"青金石",
"future":"",
"resultcode":"200",
"error_code":0
}
 */
data class YearData(
    val career: List<String>,
    val date: String,
    val error_code: Int,
    val finance: List<String>,
    val future: String,
    val health: List<String>,
    val love: List<String>,
    val luckeyStone: String,
    val mima: Mima,
    val name: String,
    val resultcode: String,
    val year: Int
)

data class Mima(
    val info: String,
    val text: List<String>
)