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
//����
//----------------------------------------------------------------------
if(type=="external") {
	var cmn01 = ''
	+'<link rel="stylesheet" type="text/css" href="' + externalPath + 'shared/cmn_footer/html/' + color + '/config.css" media="screen, print" />'
	+'<div id="' + color + '" class="group footer-nav">'
	+'<div class="group inner" style="width:' + width + ';">'
	+'<p class="ci"><a href="' + externalPath + 'index.html" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/ci.png" width="110" height="19" alt="�������z�[��" class="png" /></a></p>'
	+'<ul class="group">'
	+'';
} else {
	var cmn01 = ''
	+'<link rel="stylesheet" type="text/css" href="' + path2 + 'shared/cmn_footer/html/' + color + '/config.css" media="screen, print" />'
	+'<div id="' + color + '" class="group footer-nav">'
	+'<div class="group inner" style="width:' + width + ';">'
	+'<p class="ci"><a href="' + path2 + 'index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/ci.png" width="110" height="19" alt="�������z�[��" class="png" /></a></p>'
	+'<ul class="group">'
	+'';
}

//Copyright �Ȃ�
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

//Copyright �Ȃ�
var cmn03 = ''
+'</ul>'
+'</div>'
+'</div>'
+'';

//----------------------------------------------------------------------
//���i�u�����h�T�C�g�p�Fproducts
//----------------------------------------------------------------------
var output = ''
+'<li id="nvf01"><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li id="nvf05"><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf05.png" width="98" height="11" alt="���̏��i�J�^���O��" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li id="nvf03"><a href="' + path2 + 'customer/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf03.png" width="70" height="11" alt="���q���ܑ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';
//----------------------------------------------------------------------
//���̑��X�y�V�����T�C�g�p�Fothers
//----------------------------------------------------------------------
var output2 = ''
+'<li id="nvf01"><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li id="nvf03"><a href="' + path2 + 'customer/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf03.png" width="70" height="11" alt="���q���ܑ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';
//----------------------------------------------------------------------
//��Ə��X�y�V�����T�C�g�p�Fcorp (2011/11�ύX)
//----------------------------------------------------------------------
var output3 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="http://group.shiseido.co.jp/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_corporate.png" width="79" height="11" alt="��Ə��g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="http://group.shiseido.co.jp/inquiry/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_contact.png" height="11" alt="���₢���킹" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//�O���[�v��Зp�Fgroup (2011/11�ύX)
//----------------------------------------------------------------------
var output4 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="�����p�ɍۂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="�l���ɂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//���i�u�����h�T�C�g�p (1)�Fproducts1 (2011/11�ǉ�)
//----------------------------------------------------------------------
var output5 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="���i�J�^���O" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_shop + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_shop.png" height="11" alt="�I�����C���V���b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_navi + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_navi.png" height="11" alt="���X�i�r" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="�����p�ɍۂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="�l���ɂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//���i�u�����h�T�C�g�p (2)�Fproducts2 (2011/11�ǉ�)
//----------------------------------------------------------------------
var output6 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="���i�J�^���O" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_shop + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_shop.png" height="11" alt="�I�����C���V���b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="�����p�ɍۂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="�l���ɂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//���i�u�����h�T�C�g�p (3)�Fproducts3 (2011/11�ǉ�)
//----------------------------------------------------------------------
var output7 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="���i�J�^���O" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_navi + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_navi.png" height="11" alt="���X�i�r" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="�����p�ɍۂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="�l���ɂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//���i�u�����h�T�C�g�p (4)�Fproducts4 (2011/11�ǉ�)
//----------------------------------------------------------------------
var output8 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="���i�J�^���O" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="�����p�ɍۂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="�l���ɂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//�t���[���p (1)�Fframe1 (2012/2�ǉ�)
//----------------------------------------------------------------------
var output9 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="/sw/products/SWFG070010.seam?online_shohin_ctlg_kbn=2" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="���i�J�^���O" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="/sw/products/SWFG070010.seam?online_shohin_ctlg_kbn=1" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_shop.png" height="11" alt="�I�����C���V���b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="�����p�ɍۂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="�l���ɂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//�t���[���p (2)�Fframe2 (2012/2�ǉ�)
//----------------------------------------------------------------------
var output10 = ''
+'<li><a href="' + path2 + 'wp/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="/sw/products/SWFG070010.seam?online_shohin_ctlg_kbn=2" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="���i�J�^���O" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'customer/index.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="�����p�ɍۂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="�l���ɂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//�ʃh���C���p�Fexternal (2012/2�ǉ�)
//----------------------------------------------------------------------
var output11 = ''
+'<li><a href="' + externalPath + 'wp/index.html" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_top.png" height="11" alt="���^�V�v���X�g�b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_catalog + '" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_catalog.png" height="11" alt="���i�J�^���O" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_shop + '" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_shop.png" height="11" alt="�I�����C���V���b�v" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + url_navi + '" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_navi.png" height="11" alt="���X�i�r" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalPath + 'customer/index.html" target="_blank"><img src="' + externalPath + 'shared/cmn_footer/html/' + color + '/nvf_customer.png" height="11" alt="���q���܃T�|�[�g" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + path3 + 'term_of_use.html" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_term.png" height="11" alt="�����p�ɍۂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'<li><a href="' + externalGroupPath + 'privacy-policy/" target="_blank"><img src="' + path2 + 'shared/cmn_footer/html/' + color + '/nvf_info.png" height="11" alt="�l���ɂ���" onmouseover="this.src=this.src.replace(\'.png\',\'_on.png\')" onmouseout="this.src=this.src.replace(\'_on.png\',\'.png\')" class="png" /></a></li>'
+'';

//----------------------------------------------------------------------
//�o��
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
	// 2011/11/11�ǉ�
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
	// 2012/2/21�ǉ�
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