//Version : CLSTK20130710

path = "/sp/";
path2 = "/";
externalGroupPath = "http://www.shiseidogroup.jp/";
copyright = "Copyright &copy; Shiseido Japan Co.,Ltd. All Rights Reserved."

function footerNavi(type, color, url_shop, url_navi, url_catalog){
	document.write('<link rel="stylesheet" type="text/css" href="' + path + 'shared/cmn_footer/html/footer_nav.css" media="screen, print" />');
//----------------------------------------------------------------------
//共通
//----------------------------------------------------------------------

var cmn01 = ''
+'<link rel="stylesheet" type="text/css" href="' + path + 'shared/cmn_footer/html/' + color + '/config.css" media="screen, print" />'
+'<div id="' + color + '" class="group footer-nav">'
+'<div class="group inner">'
+'<ul class="group">'
+'';

//Copyright
var cmn02 = ''
+'</ul>'
+'<div class="ci-area">'
+'<p class="ci"><a href="' + path + 'index.html" target="_blank"><img src="' + path + 'shared/cmn_footer/html/' + color + '/ci.png" width="75" height="13" alt="資生堂ホーム" /></a></p>'
+'<p class="copyright">' + copyright + '</p>'
+'</div>'
+'</div>'
+'</div>'
+'';

//----------------------------------------------------------------------
//スペシャルサイト用：products1
//----------------------------------------------------------------------
var output = ''
+'<li><a target="_blank" href="' + path + 'wp/index.html">ワタシプラストップ</a></li><li><a target="_blank" href="' + url_shop + '">オンラインショップ</a></li><li><a target="_blank" href="' + url_navi + '">お店ナビ</a></li><li><a target="_blank" href="' + path2 + 'customer/index.html">お客さまサポート</a></li><li><a class="color-gold" target="_blank" href="' + path2 + 'term_of_use.html">ご利用に際して</a></li><li><a class="color-gold" target="_blank" href="' + externalGroupPath + 'privacy-policy/">個人情報について</a></li>'
+'';
//----------------------------------------------------------------------
//スペシャルサイト用：products2
//----------------------------------------------------------------------
var output2 = ''
+'<li><a target="_blank" href="' + path + 'wp/index.html">ワタシプラストップ</a></li><li><a target="_blank" href="' + url_navi + '">お店ナビ</a></li><li><a target="_blank" href="' + path2 + 'customer/index.html">お客さまサポート</a></li><li><a class="color-gold" target="_blank" href="' + path2 + 'term_of_use.html">ご利用に際して</a></li><li><a class="color-gold" target="_blank" href="' + externalGroupPath + 'privacy-policy/">個人情報について</a></li>'
+'';
//----------------------------------------------------------------------
//ブランドサイト4：products4
//----------------------------------------------------------------------
	var output3 = ''
		+'<li><a target="_blank" href="' + path + 'wp/index.html">ワタシプラストップ</a></li><li><a target="_blank" href="' + url_catalog + '">商品カタログ</a></li><li><a target="_blank" href="' + path2 + 'customer/index.html">お客さまサポート</a></li><li><a class="color-gold" target="_blank" href="' + path2 + 'term_of_use.html">ご利用に際して</a></li><li><a class="color-gold" target="_blank" href="' + externalGroupPath + 'privacy-policy/">個人情報について</a></li>'
		+'';
//----------------------------------------------------------------------
//その他スペシャルサイト：group
//----------------------------------------------------------------------
	var output4 = ''
		+'<li><a target="_blank" href="' + path + 'wp/index.html">ワタシプラストップ</a></li><li><a target="_blank" href="' + path2 + 'customer/index.html">お客さまサポート</a></li><li><a class="color-gold" target="_blank" href="' + path2 + 'term_of_use.html">ご利用に際して</a></li><li><a class="color-gold" target="_blank" href="' + externalGroupPath + 'privacy-policy/">個人情報について</a></li>'
		+'';


//----------------------------------------------------------------------
//出力
//----------------------------------------------------------------------
document.write(cmn01);
	if(type=="products1") { 
		document.write(output);
		document.write(cmn02);
	} else if(type=="products2") { 
		document.write(output2);
		document.write(cmn02);
	} else if (type=="products4") {
		document.write(output3);
		document.write(cmn02);
	} else if (type=="group") {
		document.write(output4);
		document.write(cmn02);
	}
document.write('<script type="text/javascript" src="//www.shiseido.co.jp/shared/js/sc/s_code.js"></script>');
document.write('<script type="text/javascript" src="//www.shiseido.co.jp/shared/js/sc/s_code_do.js"></script>');
}
//EOF