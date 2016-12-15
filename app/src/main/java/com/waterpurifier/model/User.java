package com.waterpurifier.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bin.shen on 11/12/2016.
 */

public class User implements Serializable {

    public String _id;

    public String tel;

    public String password;

    public String name;

    public BigDecimal amount;
}
