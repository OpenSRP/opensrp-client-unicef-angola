package org.smartregister.unicefangola;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.smartregister.unicefangola.shadow.CustomFontTextViewShadow;
import org.smartregister.unicefangola.shadow.ShadowAssetHandler;
import org.smartregister.unicefangola.shadow.ShadowBaseJob;

/**
 * Created by Ephraim Kigamba - nek.eam@gmail.com on 05-03-2020.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {27}, shadows = {ShadowBaseJob.class, ShadowAssetHandler.class, CustomFontTextViewShadow.class}, application = TestUnicefAngolaApplication.class)
public abstract class BaseRobolectricTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

}
