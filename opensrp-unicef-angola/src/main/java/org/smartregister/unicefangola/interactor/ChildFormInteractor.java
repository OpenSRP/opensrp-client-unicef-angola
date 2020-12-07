package org.smartregister.unicefangola.interactor;

import com.vijay.jsonwizard.constants.JsonFormConstants;
import com.vijay.jsonwizard.interactors.JsonFormInteractor;

import org.smartregister.child.widgets.ChildCheckboxTextFactory;
import org.smartregister.child.widgets.ChildEditTextFactory;
import org.smartregister.child.widgets.ChildSpinnerFactory;
import org.smartregister.unicefangola.widget.AdverseEffectDatePickerFactory;
import org.smartregister.unicefangola.widget.AppMultiSelectListFactory;

public class ChildFormInteractor extends JsonFormInteractor {

    private static final ChildFormInteractor instance = new ChildFormInteractor();

    private ChildFormInteractor() {
        super();
    }

    public static ChildFormInteractor getInstance() {
        return instance;
    }

    @Override
    protected void registerWidgets() {
        super.registerWidgets();
        map.put(JsonFormConstants.EDIT_TEXT, new ChildEditTextFactory());
        map.put(JsonFormConstants.DATE_PICKER, new AdverseEffectDatePickerFactory());
        map.put(JsonFormConstants.CHECK_BOX, new ChildCheckboxTextFactory());
        map.put(JsonFormConstants.SPINNER, new ChildSpinnerFactory());
        map.put(JsonFormConstants.MULTI_SELECT_LIST, new AppMultiSelectListFactory());
    }
}
