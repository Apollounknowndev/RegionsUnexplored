package net.regions_unexplored.config.json5;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.BaseMapCodec;

import java.util.Map;

public record CommentedUnboundedMapCodec<K, V>(Codec<K> keyCodec, Codec<V> elementCodec, Map<K, String> comments) implements BaseMapCodec<K, V>, Codec<Map<K, V>> {
	@Override
	public <T> DataResult<Pair<Map<K, V>, T>> decode(final DynamicOps<T> ops, final T input) {
		return ops.getMap(input).setLifecycle(Lifecycle.stable()).flatMap(map -> decode(ops, map)).map(r -> Pair.of(r, input));
	}
	
	@Override
	public <T> DataResult<T> encode(final Map<K, V> input, final DynamicOps<T> ops, final T prefix) {
		var encoded = encode(input, ops, ops.mapBuilder());
		if (encoded instanceof Json5Ops.Json5RecordBuilder json5builder) {
			for (var entry : this.comments.entrySet()) {
				json5builder.putComment(entry.getKey().toString(), entry.getValue());
			}
		}
		return encoded.build(prefix);
	}
	
	@Override
	public String toString() {
		return "UnboundedMapCodec[" + keyCodec + " -> " + elementCodec + ']';
	}
}

