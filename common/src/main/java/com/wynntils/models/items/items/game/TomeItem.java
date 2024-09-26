/*
 * Copyright © Wynntils 2023-2024.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.models.items.items.game;

import com.wynntils.models.character.type.ClassType;
import com.wynntils.models.gear.type.GearTier;
import com.wynntils.models.items.properties.GearTierItemProperty;
import com.wynntils.models.items.properties.IdentifiableItemProperty;
import com.wynntils.models.items.properties.RerollableItemProperty;
import com.wynntils.models.rewards.type.TomeInfo;
import com.wynntils.models.rewards.type.TomeInstance;
import com.wynntils.models.stats.type.StatActualValue;
import com.wynntils.models.stats.type.StatPossibleValues;
import com.wynntils.models.stats.type.StatType;
import com.wynntils.utils.type.Pair;
import com.wynntils.utils.type.RangedValue;
import java.util.List;
import java.util.Optional;

public class TomeItem extends GameItem
        implements GearTierItemProperty, RerollableItemProperty, IdentifiableItemProperty<TomeInfo, TomeInstance> {
    private TomeInfo tomeInfo;
    private final TomeInstance tomeInstance;

    public TomeItem(TomeInfo tomeInfo, TomeInstance tomeInstance) {
        this.tomeInfo = tomeInfo;
        this.tomeInstance = tomeInstance;
    }

    @Override
    public TomeInfo getItemInfo() {
        return tomeInfo;
    }

    @Override
    public void setItemInfo(TomeInfo itemInfo) {
        this.tomeInfo = tomeInfo;
    }

    @Override
    public Optional<TomeInstance> getItemInstance() {
        return Optional.ofNullable(tomeInstance);
    }

    @Override
    public int getRerollCount() {
        return tomeInstance != null ? tomeInstance.rerolls() : 0;
    }

    @Override
    public GearTier getGearTier() {
        return tomeInfo.tier();
    }

    @Override
    public String getName() {
        return tomeInfo.name();
    }

    @Override
    public ClassType getRequiredClass() {
        // Tomes are not class-specific
        return null;
    }

    @Override
    public List<StatType> getVariableStats() {
        return tomeInfo.variableStats().stream().map(Pair::a).toList();
    }

    @Override
    public List<StatActualValue> getIdentifications() {
        if (tomeInstance == null) return List.of();

        return tomeInstance.identifications();
    }

    @Override
    public List<StatPossibleValues> getPossibleValues() {
        return tomeInfo.variableStats().stream().map(Pair::b).toList();
    }

    @Override
    public RangedValue getIdentificationLevelRange() {
        // Tomes do not have level-specific stats
        return RangedValue.NONE;
    }

    @Override
    public boolean hasOverallValue() {
        return tomeInstance != null && tomeInstance.hasOverallValue();
    }

    @Override
    public boolean isPerfect() {
        return tomeInstance != null && tomeInstance.isPerfect();
    }

    @Override
    public boolean isDefective() {
        return tomeInstance != null && tomeInstance.isDefective();
    }

    @Override
    public float getOverallPercentage() {
        return tomeInstance != null ? tomeInstance.getOverallPercentage() : 0.0f;
    }

    @Override
    public String toString() {
        return "TomeItem{" + "tomeInfo=" + tomeInfo + ", tomeInstance=" + tomeInstance + '}';
    }
}
