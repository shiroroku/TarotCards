package shiroroku.tarotcards.Container;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import shiroroku.tarotcards.TarotCards;

public class TarotDeckScreen extends AbstractContainerScreen<TarotDeckContainer> {

	private final ResourceLocation GUI = new ResourceLocation(TarotCards.MODID, "textures/gui/tarot_deck.png");

	public TarotDeckScreen(TarotDeckContainer container, Inventory inv, Component name) {
		super(container, inv, name);
	}

	@Override
	public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(GuiGraphics matrixStack, int mouseX, int mouseY) {
		Component text = Component.translatable("item.tarotcards.tarot_deck");
		matrixStack.drawString(Minecraft.getInstance().font, text, this.imageWidth / 2 - Minecraft.getInstance().font.width(text) / 2, 8, 0xfff699);
	}

	@Override
	protected void renderBg(GuiGraphics matrixStack, float partialTicks, int mouseX, int mouseY) {
		int relX = (this.width - this.imageWidth) / 2;
		int relY = (this.height - this.imageHeight) / 2;
		matrixStack.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
	}

}

