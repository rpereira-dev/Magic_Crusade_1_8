package fr.toss.magiccrusade.client.entity.render;

import fr.toss.magiccrusade.client.entity.model.ModelSnowCube;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSnowCube extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation("magiccrusade:textures/entity/snow_cube.png");

    public RenderSnowCube(RenderManager render, ModelSnowCube modelSnowCube, float size)
    {
        super(render, modelSnowCube, size);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity e)
    {
        return (texture);
    }
}