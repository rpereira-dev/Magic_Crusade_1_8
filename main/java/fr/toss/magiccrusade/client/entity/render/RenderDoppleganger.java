package fr.toss.magiccrusade.client.entity.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDoppleganger extends RenderBiped
{
    private static final ResourceLocation texture = new ResourceLocation("magiccrusade:textures/entity/Doplleganger.png");

    public RenderDoppleganger(RenderManager render, ModelBiped model, float size)
    {
        super(render, model, size);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity e)
    {
        return (texture);
    }
}