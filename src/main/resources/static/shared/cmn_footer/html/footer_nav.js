//Version : CLSTK20091210

path = "/";
path2 = "/";
//path2 = "http://www.shiseido.co.jp/";
path3 = "/";
externalPath = "http://www.shiseido.co.jp/";
externalGroupPath = "http://www.shiseidogroup.jp/";
if(location.host.indexOf('tsubaki-ht-pc') != -1 || location.host.indexOf('tbk.dev.') != -1 || location.host.indexOf('.localhost') != -1){
	externalPath = "/";
}

function footerNavi(type, color, width, url_catalog, url_shop, url_navi){
if(type=="external") {
	document.write('<link rel="stylesheet" type="text/css" href="' + externalPath + 'shared/cmn_footer/html/footer_nav.css" media="screen, print" />');
} else {
	document.write('<link rel="stylesheet" type="text/css" href="' + path2 + 'shared/cmn_footer/html/footer_nav.css" media="screen, print" />');
}
//----------------------------------------------------------------------
//共通
//----------------------------------------------------------------------
if(type=="external") {
	var cmn01 = ''
	+'<link rel="stylesheet" type="text/css" href="' + externalPath + 'shared/cmn_footer/html/' + color + '/config.css" media="screen, print" />'
	+'<div id="' + color + '" class="group footer-nav">'
	+'<div class="group inner" style="width:' + width + ';">'
	+'<p class="ci"><a href="' + externalPath + 'index.html" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/ci.png" width="110" height="19" alt="資生堂ホーム" class="png" /></a></p>'
	+'<ul class="group">'
	+'';
} else {
	var cmn01 = ''
	+'<link rel="stylesheet" type="text/css" href="' + path2 + 'shared/cmn_footer/html/' + color + '/config.css" media="screen, print" />'
	+'<div id="' + color + '" class="group footer-nav">'
	+'<div class="group inner" style="width:' + width + ';">'
	+'<p class="ci"><a href="' + path2 + 'index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/ci.png" width="110" height="19" alt="資生堂ホーム" class="png" /></a></p>'
	+'<ul class="group">'
	+'';
}

//Copyright なし
if(type=="external") {
	var cmn02 = ''
	+'</ul>'
	+'<p class="copyright"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/copyright.png" width="253" height="8" alt="Copyright &copy; Shiseido Co.,Ltd. All Rights Reserved." class="png" /></p>'
	+'</div>'
	+'</div>'
	+'';
} else {
	var cmn02 = ''
	+'</ul>'
	+'<p class="copyright"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/copyright.png" width="253" height="8" alt="Copyright &copy; Shiseido Co.,Ltd. All Rights Reserved." class="png" /></p>'
	+'</div>'
	+'</div>'
	+'';
}

//Copyright なし
var cmn03 = ''
+'</ul>'
+'</div>'
+'</div>'
+'';

//----------------------------------------------------------------------
//商品ブランドサイト用：products
//----------------------------------------------------------------------
var output = ''
+'<li id="nvf01"><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li id="nvf05"><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf05.png" width="98" height="11" alt="この商品カタログへ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li id="nvf03"><a href="' + path2 + 'customer/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf03.png" width="70" height="11" alt="お客さま窓口" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';
//----------------------------------------------------------------------
//その他スペシャルサイト用：others
//----------------------------------------------------------------------
var output2 = ''
+'<li id="nvf01"><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li id="nvf03"><a href="' + path2 + 'customer/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf03.png" width="70" height="11" alt="お客さま窓口" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';
//----------------------------------------------------------------------
//企業情報スペシャルサイト用：corp (2011/11変更)
//----------------------------------------------------------------------
var output3 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="http://group.shiseido.co.jp/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_corporate.png" width="79" height="11" alt="企業情報トップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="http://group.shiseido.co.jp/inquiry/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_contact.png" height="11" alt="お問い合わせ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//グループ会社用：group (2011/11変更)
//----------------------------------------------------------------------
var output4 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="ご利用に際して" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="個人情報について" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//商品ブランドサイト用 (1)：products1 (2011/11追加)
//----------------------------------------------------------------------
var output5 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="商品カタログ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_shop + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_shop.png" height="11" alt="オンラインショップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_navi + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_navi.png" height="11" alt="お店ナビ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="ご利用に際して" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="個人情報について" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//商品ブランドサイト用 (2)：products2 (2011/11追加)
//----------------------------------------------------------------------
var output6 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="商品カタログ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_shop + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_shop.png" height="11" alt="オンラインショップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="ご利用に際して" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="個人情報について" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//商品ブランドサイト用 (3)：products3 (2011/11追加)
//----------------------------------------------------------------------
var output7 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="商品カタログ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_navi + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_navi.png" height="11" alt="お店ナビ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="ご利用に際して" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="個人情報について" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//商品ブランドサイト用 (4)：products4 (2011/11追加)
//----------------------------------------------------------------------
var output8 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="商品カタログ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="ご利用に際して" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="個人情報について" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//フレーム用 (1)：frame1 (2012/2追加)
//----------------------------------------------------------------------
var output9 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="/sw/products/SWFG070010.seam?online_shohin_ctlg_kbn=2" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="商品カタログ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="/sw/products/SWFG070010.seam?online_shohin_ctlg_kbn=1" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_shop.png" height="11" alt="オンラインショップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="ご利用に際して" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="個人情報について" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//フレーム用 (2)：frame2 (2012/2追加)
//----------------------------------------------------------------------
var output10 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="/sw/products/SWFG070010.seam?online_shohin_ctlg_kbn=2" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="商品カタログ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="ご利用に際して" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="個人情報について" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//別ドメイン用：external (2012/2追加)
//----------------------------------------------------------------------
var output11 = ''
+'<li><a href="' + externalPath + 'wp/index.html" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="ワタシプラストップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="商品カタログ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_shop + '" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_shop.png" height="11" alt="オンラインショップ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_navi + '" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_navi.png" height="11" alt="お店ナビ" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalPath + 'customer/index.html" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="お客さまサポート" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="ご利用に際して" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="個人情報について" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//出力
//----------------------------------------------------------------------
document.write(cmn01);
	if(type=="products") { 
		document.write(output);
		document.write(cmn02);
	} else if(type=="others") { 
		document.write(output2);
		document.write(cmn02);
	} else if(type=="corp") { 
		document.write(output3);
		document.write(cmn02);
	} else if(type=="group") { 
		document.write(output4);
		document.write(cmn02);
	} else if(type=="group2") { 
		document.write(output4);
		document.write(cmn02);
	// 2011/11/11追加
	} else if(type=="products1") { 
		document.write(output5);
		document.write(cmn02);
	} else if(type=="products2") { 
		document.write(output6);
		document.write(cmn02);
	} else if(type=="products3") { 
		document.write(output7);
		document.write(cmn02);
	} else if(type=="products4") { 
		document.write(output8);
		document.write(cmn02);
	// 2012/2/21追加
	} else if(type=="frame1") { 
		document.write(output9);
		document.write(cmn02);
	} else if(type=="frame2") { 
		document.write(output10);
		document.write(cmn02);
	} else if(type=="external") { 
		document.write(output11);
		document.write(cmn02);
	}
document.write('<script type="text/javascript" src="//www.shiseido.co.jp/shared/js/sc/s_code.js"></script>');
document.write('<script type="text/javascript" src="//www.shiseido.co.jp/shared/js/sc/s_code_do.js"></script>');
}

//EOF