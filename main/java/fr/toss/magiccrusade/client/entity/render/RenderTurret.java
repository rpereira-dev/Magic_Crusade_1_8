package fr.toss.magiccrusade.client.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.entity.model.ModelTurret;

@SideOnly(Side.CLIENT)
public class RenderTurret extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation("magiccrusade:textures/entity/turret.png");
    
    public RenderTurret(RenderManager render, ModelTurret model, float size)
    {
        super(render, model, size);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity e)
    {
        return (texture);
    }
}