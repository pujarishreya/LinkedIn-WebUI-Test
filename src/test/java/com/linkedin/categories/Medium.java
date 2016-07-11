package com.linkedin.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

/**
 * Created by shreya on 4/11/16.
 */

@RunWith(Categories.class)
@Categories.IncludeCategory({Medium.class})
public interface Medium {
}
