package fr.toss.magiccrusade.client.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.entity.model.ModelBelier;
import fr.toss.magiccrusade.common.entity.EntityBelier;

@SideOnly(Side.CLIENT)
public class RenderBelier extends RenderLiving
{
    private static final ResourceLocation texture1 = new ResourceLocation("magiccrusade:textures/entity/belier.png");
    private static final ResourceLocation texture2 = new ResourceLocation("magiccrusade:textures/entity/belier_golden.png");
    private static final ResourceLocation texture3 = new ResourceLocation("magiccrusade:textures/entity/belier_green.png");

    
    public RenderBelier(RenderManager render, ModelBelier modelBelier, float size)
    {
        super(render, modelBelier, size);
    }


    @Override
    protected ResourceLocation getEntityTexture(Entity e)
    {
    	EntityBelier	entity;
    	
    	entity = (EntityBelier)e;
    	switch (entity.type)
    	{
    		case 0 :
        		return (texture1);
        		
    		case 1 :
        		return (texture2);
        		
       		case 2 :
        		return (texture3);
    	}
        return (texture1);
    }
}