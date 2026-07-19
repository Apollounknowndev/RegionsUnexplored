package net.regions_unexplored.datagen.provider.client;

import com.google.gson.JsonObject;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public final class ModelBuilder {
	private String parent = null;
	private Map<String, String> textures = new HashMap<>();
	
	public static ModelBuilder builder() {
		return new ModelBuilder();
	}
	
	public ModelBuilder parent(String name) {
		this.parent = name;
		return this;
	}
	
	public ModelBuilder texture(String key, Identifier value) {
		this.textures.put(key, value.toString());
		return this;
	}
	
	public Identifier createTemplate(Identifier id, String prefix, BiConsumer<Identifier, ModelInstance> output) {
		id = id.withPrefix(prefix);
		
		output.accept(id, () -> {
			JsonObject result = new JsonObject();
			if (this.parent != null) {
				result.addProperty("parent", this.parent);
			}
			JsonObject textureSet = new JsonObject();
			for (Map.Entry<String, String> entry : this.textures.entrySet()) {
				textureSet.addProperty(entry.getKey(), entry.getValue());
			}
			result.add("textures", textureSet);
			return result;
		});
		
		return id;
	}
}
