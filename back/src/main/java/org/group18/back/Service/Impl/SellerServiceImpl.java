package org.group18.back.Service.Impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.group18.back.Dao.UserMapper;
import org.group18.back.Entity.*;
import org.group18.back.Service.SellerService;
import org.group18.back.Dao.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import com.qcloud.cos.*;
import com.qcloud.cos.auth.*;
import com.qcloud.cos.region.*;
import com.qcloud.cos.exception.*;
import com.qcloud.cos.model.*;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public Map<String,String> newSellerShop(Shop shop,MultipartFile mfile){
        Map<String,String> result = new HashMap<>();
        if(shop.getShopName()==""||shop.getDescribes()==""||shop.getAddress()==""||mfile.isEmpty())
        {
            result.put("error","输入字段不能为空");
        }
        else{
            ShopExample shopExample1 = new ShopExample();
            ShopExample.Criteria criteria1 = shopExample1.createCriteria();
            criteria1.andShopNameEqualTo(shop.getShopName());
            List<Shop> shopList1 = shopMapper.selectByExample(shopExample1);
            if(!shopList1.isEmpty())
            {
                result.put("error","店铺名已被占用");
            }
            else
            {
                try {
                    shop.setImgUrl(fileupload(mfile));                                          //上传文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
                UserExample userExample1 = new UserExample();                   //将该user的isseller字段更新为真;
                UserExample.Criteria criteria2 = userExample1.createCriteria();
                criteria2.andUidEqualTo(shop.getSellerUid());
                User user = new User();
                user.setIsSeller(true);
                userMapper.updateByExampleSelective(user,userExample1);

                shopMapper.insert(shop);                               //插入数据


                result.put("success","");
            }
        }
        return result;
    }
    @Override
    public String fileupload(MultipartFile mfile) throws IOException {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDXr1waf5Egy1QsJ87ZyHuHr04vcVqnqXO";
        String secretKey = "kE0jKSMgGaGNJ1SmsvMazBB0av1rumPc";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-chengdu");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        //将file类型从MultipartFile 转为 File
        //获取文件名
        String filename = mfile.getOriginalFilename();
        //获取文件后缀
        String prefix = filename.substring(filename.lastIndexOf("."));
        //给文件名加上时间标记，防止同名覆盖。
        Long t = System.currentTimeMillis();
        String string = filename+t.toString();
        final File file = File.createTempFile(t.toString(),prefix);
        mfile.transferTo(file);

        try {
            // 指定要上传的文件
            File localFile = file;
            // 指定要上传到的存储桶
            String bucketName = "suncaper-group18-1256197408";
            // 指定要上传到 COS 上对象键
            String key = "shop_img/" + file.getName();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }
        if(file.exists())
            file.delete();
        cosClient.shutdown();
        return "https://suncaper-group18-1256197408.cos.ap-chengdu.myqcloud.com/shop_img/"+file.getName();
    }

}
