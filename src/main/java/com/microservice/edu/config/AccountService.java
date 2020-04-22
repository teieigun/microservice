package com.microservice.edu.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.edu.dao.UserBaseProfileDao;
import com.microservice.edu.dao.UserRoleMstDao;
import com.microservice.edu.pojo.Account;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.UserBaseProfilePojo;
import com.microservice.edu.pojo.UserRoleMstPojo;

/**
 * ログイン認証処理を行うサービスクラスです。
 */
@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private UserBaseProfileDao userBaseProfileDao;

    @Autowired
    private UserRoleMstDao userRoleMstDao;


    List<BigCategoryTblPojo> rolelist;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String mailaddress) throws UsernameNotFoundException {

        if (mailaddress == null || "".equals(mailaddress)) {
            throw new UsernameNotFoundException("メールアドレスを入力してください。");
        }

        // 有効なユーザ情報を取得します。
        UserBaseProfilePojo UserBaseProfilePojo = null;
        try {
            UserBaseProfilePojo = userBaseProfileDao.findByVcd(mailaddress);
        } catch (Exception e) {
            System.out.println("ログイン認証処理：ユーザ情報の取得に失敗しました。");
        }
        if (UserBaseProfilePojo == null) {
            throw new UsernameNotFoundException("ユーザ情報の取得に失敗しました。");
        }

        // 有効なユーザロール情報を取得します。
        List<UserRoleMstPojo> userRoleMstPojoList= null;
        try {
            userRoleMstPojoList = userRoleMstDao.findByVcd(UserBaseProfilePojo.getEmail());
        } catch (Exception e) {
            System.out.println("ログイン認証処理：ユーザロール情報の取得に失敗しました。");
        }
        if (userRoleMstPojoList.isEmpty()) {
            throw new UsernameNotFoundException("ユーザロール情報の取得に失敗しました。");
        }

        // ユーザロール情報を設定します。
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (UserRoleMstPojo userRoleMstPojo : userRoleMstPojoList) {
            authorities.add(new SimpleGrantedAuthority(userRoleMstPojo.role));
        }

        Account account = new Account(UserBaseProfilePojo, authorities);
        return account;
    }
}