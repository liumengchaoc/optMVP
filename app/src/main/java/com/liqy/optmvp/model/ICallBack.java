package com.liqy.optmvp.model;

import java.util.List;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期四 YourCompany.
 */
public interface ICallBack {
     void success(List<Comment> data);
     void failed(String msg);
}
