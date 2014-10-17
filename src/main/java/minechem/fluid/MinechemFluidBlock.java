package minechem.fluid;

import minechem.Settings;
import minechem.fluid.reaction.ChemicalFluidReactionHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class MinechemFluidBlock extends BlockFluidClassic {

	public MinechemFluidBlock(MinechemFluid fluid, Material material) {
		super(fluid, material);
		setQuantaPerBlock(fluid.getQuanta());
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock){
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);
		
		if (Settings.reactionFluidMeetFluid){
			checkToExplode(world,x+1,y,z,x,y,z);
			checkToExplode(world,x,y+1,z,x,y,z);
			checkToExplode(world,x,y,z+1,x,y,z);
			checkToExplode(world,x-1,y,z,x,y,z);
			checkToExplode(world,x,y-1,z,x,y,z);
			checkToExplode(world,x,y,z-1,x,y,z);
		}
	}
	
	private boolean checkToExplode(World world,int dx,int dy,int dz,int sx,int sy,int sz){
		return ChemicalFluidReactionHandler.checkToExplode(this, world.getBlock(dx, dy, dz), world, dx, dy, dz,sx,sy,sz);
	}
}