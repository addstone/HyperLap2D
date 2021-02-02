package games.rednblack.editor.view.ui.properties.panels;

import com.badlogic.ashley.core.Entity;
import games.rednblack.editor.controller.commands.component.UpdateTalosDataCommand;
import games.rednblack.editor.renderer.components.particle.TalosDataComponent;
import games.rednblack.editor.renderer.data.TalosVO;
import games.rednblack.editor.view.ui.properties.UIItemPropertiesMediator;
import games.rednblack.h2d.common.MsgAPI;

public class UITalosPropertiesMediator extends UIItemPropertiesMediator<Entity, UITalosProperties> {

    private static final String TAG = UITalosPropertiesMediator.class.getCanonicalName();
    public static final String NAME = TAG;

    public UITalosPropertiesMediator() {
        super(NAME, new UITalosProperties());
    }

    @Override
    protected void translateObservableDataToView(Entity item) {
        viewComponent.setMatrixTransformEnabled(item.getComponent(TalosDataComponent.class).transform);
    }

    @Override
    protected void translateViewToItemData() {
        TalosVO payloadVo = new TalosVO();
        payloadVo.transform = viewComponent.isMatrixTransformEnabled();

        Object payload = UpdateTalosDataCommand.payload(observableReference, payloadVo);
        facade.sendNotification(MsgAPI.ACTION_UPDATE_TALOS_DATA, payload);
    }
}
