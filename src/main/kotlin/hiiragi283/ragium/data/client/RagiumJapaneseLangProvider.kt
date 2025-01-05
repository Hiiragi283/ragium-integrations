package hiiragi283.ragium.data.client

import hiiragi283.ragium.api.RagiumAPI
import hiiragi283.ragium.api.machine.HTMachineTier
import hiiragi283.ragium.api.material.HTTagPrefix
import hiiragi283.ragium.common.init.RagiumBlocks
import hiiragi283.ragium.common.init.RagiumEntityTypes
import hiiragi283.ragium.common.init.RagiumFluids
import hiiragi283.ragium.common.init.RagiumItemGroup
import hiiragi283.ragium.common.init.RagiumItems
import hiiragi283.ragium.common.init.RagiumMachineKeys
import hiiragi283.ragium.common.init.RagiumMaterialKeys
import hiiragi283.ragium.common.init.RagiumTranslationKeys
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.world.World
import java.util.concurrent.CompletableFuture

class RagiumJapaneseLangProvider(output: FabricDataOutput, registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricLanguageProvider(output, "ja_jp", registryLookup) {
    override fun generateTranslations(registryLookup: RegistryWrapper.WrapperLookup, builder: TranslationBuilder) {
        RagiumFluids.entries.forEach { fluid: RagiumFluids ->
            builder.add(
                fluid.translationKey,
                fluid.jaName,
            )
        }

        builder.add("modmenu.descriptionTranslation.ragium", "Fabric向けの新しい工業mod")
        builder.add("modmenu.nameTranslation.ragium", RagiumAPI.MOD_NAME)
        builder.add("text.autoconfig.ragium.title", RagiumAPI.MOD_NAME)
        builder.add(RagiumTranslationKeys.FOR_INTEGRATION, "他modとの連携用のコンテンツ")
        builder.add(RagiumTranslationKeys.PRESS_CTRL, "Ctrlキーを押して説明を表示")
        // Advancements
        builder.add(RagiumTranslationKeys.ADVANCEMENT_BUJIN, "タイクーン将軍")
        builder.add(RagiumTranslationKeys.ADVANCEMENT_STELLA_SUIT, "ｽｺﾞｲ ﾂﾖｸﾃ ｴｹﾞﾂﾅｲｸﾗｲ Love-Loveﾅ ｱｰﾏｰ")
        builder.add(RagiumTranslationKeys.ADVANCEMENT_THIS_CAKE_IS_DIE, "つばさレストラン名物「デスケーキ」")
        // Blocks
        builder.add(RagiumBlocks.Creatives.CRATE, "クリエイティブ用クレート")
        builder.add(RagiumBlocks.Creatives.DRUM, "クリエイティブ用ドラム")
        builder.add(RagiumBlocks.Creatives.EXPORTER, "クリエイティブ用搬出機")
        builder.add(RagiumBlocks.Creatives.SOURCE, "クリエイティブ用エネルギー源")

        builder.add(RagiumBlocks.MUTATED_SOIL, "変異した土壌")
        builder.add(RagiumBlocks.POROUS_NETHERRACK, "多孔質ネザーラック")

        builder.add(RagiumBlocks.Slabs.ASPHALT, "アスファルトのハーフブロック")
        builder.add(RagiumBlocks.Slabs.GYPSUM, "石膏のハーフブロック")
        builder.add(RagiumBlocks.Slabs.POLISHED_ASPHALT, "磨かれたアスファルトのハーフブロック")
        builder.add(RagiumBlocks.Slabs.POLISHED_GYPSUM, "磨かれた石膏のハーフブロック")
        builder.add(RagiumBlocks.Slabs.POLISHED_SLATE, "磨かれたスレートのハーフブロック")
        builder.add(RagiumBlocks.Slabs.SLATE, "スレートのハーフブロック")
        builder.add(RagiumBlocks.Stairs.ASPHALT, "アスファルトの階段")
        builder.add(RagiumBlocks.Stairs.GYPSUM, "石膏の階段")
        builder.add(RagiumBlocks.Stairs.POLISHED_ASPHALT, "磨かれたアスファルトの階段")
        builder.add(RagiumBlocks.Stairs.POLISHED_GYPSUM, "磨かれた石膏の階段")
        builder.add(RagiumBlocks.Stairs.POLISHED_SLATE, "磨かれたスレートの階段")
        builder.add(RagiumBlocks.Stairs.SLATE, "スレートの階段")
        builder.add(RagiumBlocks.Stones.ASPHALT, "アスファルト")
        builder.add(RagiumBlocks.Stones.GYPSUM, "石膏")
        builder.add(RagiumBlocks.Stones.POLISHED_ASPHALT, "磨かれたアスファルト")
        builder.add(RagiumBlocks.Stones.POLISHED_GYPSUM, "磨かれた石膏")
        builder.add(RagiumBlocks.Stones.POLISHED_SLATE, "磨かれたスレート")
        builder.add(RagiumBlocks.Stones.SLATE, "スレート")

        builder.add(RagiumBlocks.WhiteLines.SIMPLE, "白線")
        builder.add(RagiumBlocks.WhiteLines.T_SHAPED, "白線（T字）")
        builder.add(RagiumBlocks.WhiteLines.CROSS, "白線（交差）")

        builder.add(RagiumBlocks.Glasses.STEEL, "鋼鉄ガラス")
        builder.add(RagiumBlocks.Glasses.OBSIDIAN, "黒曜石ガラス")
        builder.add(RagiumBlocks.Glasses.RAGIUM, "ラギウムガラス")

        builder.add(RagiumBlocks.PLASTIC_BLOCK, "プラスチックブロック")

        builder.add(RagiumBlocks.SPONGE_CAKE, "スポンジケーキ")
        builder.add(RagiumBlocks.SWEET_BERRIES_CAKE, "スイートベリーケーキ")

        builder.add(RagiumBlocks.AUTO_ILLUMINATOR, "光源置き太郎")
        builder.add(RagiumBlocks.BACKPACK_INTERFACE, "バックパックインターフェース")
        builder.add(RagiumBlocks.ITEM_DISPLAY, "アイテムティスプレイ")
        builder.add(RagiumBlocks.EXTENDED_PROCESSOR, "拡張処理装置")
        builder.add(RagiumBlocks.MANUAL_FORGE, "らぎ金床")
        builder.add(RagiumBlocks.MANUAL_GRINDER, "らぎ臼")
        builder.add(RagiumBlocks.MANUAL_MIXER, "らぎ釜")
        builder.add(RagiumBlocks.NETWORK_INTERFACE, "E.N.I.")
        builder.add(RagiumBlocks.OPEN_CRATE, "オープンクレート")
        builder.add(RagiumBlocks.SHAFT, "シャフト")
        builder.add(RagiumBlocks.TELEPORT_ANCHOR, "テレポートアンカー")
        builder.add(RagiumBlocks.TRASH_BOX, "ゴミ箱")

        builder.add(RagiumBlocks.Pipes.STONE, "石パイプ")
        builder.add(RagiumBlocks.Pipes.WOODEN, "木製パイプ")
        builder.add(RagiumBlocks.Pipes.IRON, "鉄パイプ")
        builder.add(RagiumBlocks.Pipes.COPPER, "銅パイプ")
        builder.add(RagiumBlocks.Pipes.UNIVERSAL, "万能パイプ")

        builder.add(RagiumBlocks.CrossPipes.STEEL, "鋼鉄パイプ")
        builder.add(RagiumBlocks.CrossPipes.GOLD, "金パイプ")

        builder.add(RagiumBlocks.PipeStations.ITEM, "アイテムパイプステーション")
        builder.add(RagiumBlocks.PipeStations.FLUID, "液体パイプステーション")

        builder.add(RagiumBlocks.FilteringPipes.ITEM, "アイテムフィルタリングパイプ")
        builder.add(RagiumBlocks.FilteringPipes.FLUID, "液体フィルタリングパイプ")

        builder.add(RagiumTranslationKeys.AUTO_ILLUMINATOR, "半径%sブロックの範囲に光源を自動で設置する")
        builder.add(RagiumTranslationKeys.LARGE_PROCESSOR, "マルチブロック内の加工機械を拡張する")
        builder.add(RagiumTranslationKeys.MANUAL_GRINDER, "ホッパーなどでアイテムを搬入できる")
        builder.add(RagiumTranslationKeys.MUTATED_SOIL, "成長チャンバーで使用")
        builder.add(
            RagiumTranslationKeys.NETWORK_INTERFACE,
            "無線ネットワークと他modのエネルギーケーブルをつなげる",
        )
        builder.add(RagiumTranslationKeys.OBSIDIAN_GLASS, "黒曜石と同じ爆破耐性を持つガラス")
        builder.add(RagiumTranslationKeys.OPEN_CRATE, "搬入されたアイテムを下にドロップする")
        builder.add(RagiumTranslationKeys.PIPE_STATION, "優先して隣接したストレージに輸送する")
        builder.add(RagiumTranslationKeys.POROUS_NETHERRACK, "スポンジのように溶岩を吸い取る（使い切り）")
        builder.add(RagiumTranslationKeys.RAGIUM_GLASS, "岩盤と同じ爆破耐性を持つガラス")
        builder.add(RagiumTranslationKeys.SPONGE_CAKE, "着地時のダメージを軽減する")
        builder.add(RagiumTranslationKeys.STEEL_GLASS, "水と同じ爆破耐性を持つガラス")
        builder.add(RagiumTranslationKeys.TRASH_BOX, "搬入された「すべて」のアイテムや液体を消滅させる")

        builder.add(RagiumTranslationKeys.CRATE_CAPACITY, "容量: %s 個")

        builder.add(RagiumTranslationKeys.TRANSPORTER_FLUID_SPEED, "液体速度: %s ユニット/秒")
        builder.add(RagiumTranslationKeys.TRANSPORTER_ITEM_SPEED, "アイテム速度: %s個/秒")

        builder.add(RagiumTranslationKeys.EXPORTER_FLUID_FILTER, "現在の液体フィルタ: %s")
        builder.add(RagiumTranslationKeys.EXPORTER_ITEM_FILTER, "現在のアイテムフィルタ: %s")
        // Contents
        builder.add(RagiumTranslationKeys.BATTERY, "バッテリー")
        builder.add(RagiumTranslationKeys.CASING, "外装")
        builder.add(RagiumTranslationKeys.CIRCUIT, "回路")
        builder.add(RagiumTranslationKeys.CIRCUIT_BOARD, "回路基板")
        builder.add(RagiumTranslationKeys.COIL, "コイル")
        builder.add(RagiumTranslationKeys.CRATE, "クレート")
        builder.add(RagiumTranslationKeys.DRILL_HEAD, "ドリルヘッド")
        builder.add(RagiumTranslationKeys.DRUM, "ドラム")
        builder.add(RagiumTranslationKeys.EXPORTER, "搬出機")
        builder.add(RagiumTranslationKeys.GRATE, "格子")
        builder.add(RagiumTranslationKeys.HULL, "筐体")
        builder.add(RagiumTranslationKeys.PLASTIC, "プラスチック")
        // Entity
        builder.add(RagiumEntityTypes.DYNAMITE, "ダイナマイト")
        builder.add(RagiumEntityTypes.ANVIL_DYNAMITE, "金床ダイナマイト")
        builder.add(RagiumEntityTypes.BLAZING_DYNAMITE, "燃え盛るダイナマイト")
        builder.add(RagiumEntityTypes.BEDROCK_DYNAMITE, "岩盤ダイナマイト")
        builder.add(RagiumEntityTypes.FLATTENING_DYNAMITE, "整地用ダイナマイト")
        builder.add(RagiumEntityTypes.FROSTING_DYNAMITE, "凍えるダイナマイト")
        // Fluid
        builder.add(RagiumTranslationKeys.FLUID_AMOUNT, "液体量: %s")
        builder.add(RagiumTranslationKeys.FLUID_CAPACITY, "容量: %s")
        builder.add(RagiumTranslationKeys.FLUID_TITLE, "液体名: %s")
        builder.add(RagiumTranslationKeys.FORMATTED_FLUID, "%s B, %s ユニット")
        // Item
        builder.add(RagiumItems.SteelArmors.HELMET, "スチールのヘルメット")
        builder.add(RagiumItems.SteelArmors.CHESTPLATE, "スチールのチェストプレート")
        builder.add(RagiumItems.SteelArmors.LEGGINGS, "スチールのレギンス")
        builder.add(RagiumItems.SteelArmors.BOOTS, "スチールのブーツ")

        builder.add(RagiumItems.DeepSteelArmors.HELMET, "深層鋼のヘルメット")
        builder.add(RagiumItems.DeepSteelArmors.CHESTPLATE, "深層鋼のチェストプレート")
        builder.add(RagiumItems.DeepSteelArmors.LEGGINGS, "深層鋼のレギンス")
        builder.add(RagiumItems.DeepSteelArmors.BOOTS, "深層鋼のブーツ")

        builder.add(RagiumItems.StellaSuits.GOGGLE, "S.T.E.L.L.A.ゴーグル")
        builder.add(RagiumItems.StellaSuits.JACKET, "S.T.E.L.L.A.ジャケット")
        builder.add(RagiumItems.StellaSuits.LEGGINGS, "S.T.E.L.L.A.レギンス")
        builder.add(RagiumItems.StellaSuits.BOOTS, "S.T.E.L.L.A.ブーツ")

        builder.add(RagiumItems.SteelTools.AXE, "スチールの斧")
        builder.add(RagiumItems.SteelTools.HOE, "スチールのクワ")
        builder.add(RagiumItems.SteelTools.PICKAXE, "スチールのツルハシ")
        builder.add(RagiumItems.SteelTools.SHOVEL, "スチールのショベル")
        builder.add(RagiumItems.SteelTools.SWORD, "スチールの剣")

        builder.add(RagiumItems.DeepSteelTools.AXE, "深層鋼の斧")
        builder.add(RagiumItems.DeepSteelTools.HOE, "深層鋼のクワ")
        builder.add(RagiumItems.DeepSteelTools.PICKAXE, "深層鋼のツルハシ")
        builder.add(RagiumItems.DeepSteelTools.SHOVEL, "深層鋼のショベル")
        builder.add(RagiumItems.DeepSteelTools.SWORD, "深層鋼の剣")

        builder.add(RagiumItems.Dynamites.SIMPLE, "ダイナマイト")
        builder.add(RagiumItems.Dynamites.ANVIL, "金床ダイナマイト")
        builder.add(RagiumItems.Dynamites.BEDROCK, "岩盤ダイナマイト")
        builder.add(RagiumItems.Dynamites.BLAZING, "燃え盛るダイナマイト")
        builder.add(RagiumItems.Dynamites.FLATTENING, "整地用ダイナマイト")
        builder.add(RagiumItems.Dynamites.FROSTING, "凍えるダイナマイト")

        builder.add(RagiumItems.BACKPACK, "バックパック")
        builder.add(RagiumItems.EMPTY_FLUID_CUBE, "液体キューブ（なし）")
        builder.add(RagiumItems.FILLED_FLUID_CUBE, "液体キューブ（%s）")
        builder.add(RagiumItems.FLUID_FILTER, "液体フィルタ")
        builder.add(RagiumItems.FORGE_HAMMER, "鍛造ハンマー")
        builder.add(RagiumItems.GIGANT_HAMMER, "ギガントハンマー")
        builder.add(RagiumItems.ITEM_FILTER, "アイテムフィルタ")
        builder.add(RagiumItems.RAGI_WRENCH, "らぎレンチ")
        builder.add(RagiumItems.RAGIUM_SABER, "ラギウムセイバー")
        builder.add(RagiumItems.STELLA_SABER, "S.T.E.L.L.A.セイバー")
        builder.add(RagiumItems.TRADER_CATALOG, "行商人カタログ")

        builder.add(RagiumItems.SWEET_BERRIES_CAKE_PIECE, "一切れのスイートベリーケーキ")
        builder.add(RagiumItems.MELON_PIE, "メロンパイ")

        builder.add(RagiumItems.BUTTER, "バター")
        builder.add(RagiumItems.CARAMEL, "キャラメル")
        builder.add(RagiumItems.DOUGH, "生地")
        builder.add(RagiumItems.FLOUR, "小麦粉")

        builder.add(RagiumItems.CHOCOLATE, "チョコレート")
        builder.add(RagiumItems.CHOCOLATE_APPLE, "チョコリンゴ")
        builder.add(RagiumItems.CHOCOLATE_BREAD, "チョコパン")
        builder.add(RagiumItems.CHOCOLATE_COOKIE, "チョコレートクッキー")

        builder.add(RagiumItems.CINNAMON_STICK, "シナモンスティック")
        builder.add(RagiumItems.CINNAMON_POWDER, "シナモンパウダー")
        builder.add(RagiumItems.CINNAMON_ROLL, "シナモンロール")

        builder.add(RagiumItems.MINCED_MEAT, "ひき肉")
        builder.add(RagiumItems.MEAT_INGOT, "生肉インゴット")
        builder.add(RagiumItems.COOKED_MEAT_INGOT, "焼肉インゴット")
        builder.add(RagiumItems.CANNED_COOKED_MEAT, "焼肉缶詰")

        builder.add(RagiumItems.AMBROSIA, "アンブロシア")

        builder.add(RagiumItems.BEE_WAX, "蜜蠟")
        builder.add(RagiumItems.BLAZING_CARBON_ELECTRODE, "燃え盛る炭素電極")
        builder.add(RagiumItems.CARBON_ELECTRODE, "炭素電極")
        builder.add(RagiumItems.CHARGED_CARBON_ELECTRODE, "チャージされた炭素電極")
        builder.add(RagiumItems.COAL_CHIP, "石炭チップ")
        builder.add(RagiumItems.CRIMSON_CRYSTAL, "深紅の結晶")
        builder.add(RagiumItems.CRUDE_SILICON, "粗製シリコン")
        builder.add(RagiumItems.DEEPANT, "ディーパント")
        builder.add(RagiumItems.ENGINE, "V8エンジン")
        // builder.add(RagiumItems.ENGINEERING_PLASTIC_PLATE, "エンジニアリングプラスチック板")
        builder.add(RagiumItems.GLASS_SHARD, "ガラスの破片")
        builder.add(RagiumItems.LASER_EMITTER, "レーザーエミッタ")
        builder.add(RagiumItems.LED, "L.E.D.")
        builder.add(RagiumItems.LUMINESCENCE_DUST, "ルミネッセンスダスト")
        builder.add(RagiumItems.OBSIDIAN_TEAR, "黒曜石の涙")
        // builder.add(RagiumItems.PLASTIC_PLATE, "プラスチック板")
        builder.add(RagiumItems.POLYMER_RESIN, "高分子樹脂")
        builder.add(RagiumItems.PROCESSOR_SOCKET, "プロセッサソケット")
        builder.add(RagiumItems.PULP, "パルプ")
        builder.add(RagiumItems.RAGI_ALLOY_COMPOUND, "ラギ合金混合物")
        builder.add(RagiumItems.REFINED_SILICON, "精製シリコン")
        builder.add(RagiumItems.RESIDUAL_COKE, "残渣油コークス")
        builder.add(RagiumItems.ROCK_WOOL, "ロックウール")
        builder.add(RagiumItems.SILICON, "シリコン")
        builder.add(RagiumItems.SLAG, "スラグ")
        builder.add(RagiumItems.SOAP, "石鹸")
        builder.add(RagiumItems.SOLAR_PANEL, "太陽光パネル")
        builder.add(RagiumItems.STELLA_PLATE, "S.T.E.L.L.A.板")
        builder.add(RagiumItems.WARPED_CRYSTAL, "歪んだ結晶")

        builder.add(RagiumItems.Radioactives.URANIUM_FUEL, "ウラン燃料")
        builder.add(RagiumItems.Radioactives.PLUTONIUM_FUEL, "プルトニウム燃料")
        builder.add(RagiumItems.Radioactives.YELLOW_CAKE, "イエローケーキ")
        builder.add(RagiumItems.Radioactives.YELLOW_CAKE_PIECE, "一切れのイエローケーキ")
        builder.add(RagiumItems.Radioactives.NUCLEAR_WASTE, "核廃棄物")

        builder.add(RagiumItems.RAGI_TICKET, "らぎチケット")

        builder.add(RagiumItems.Processors.DIAMOND, "ダイヤモンドプロセッサ")
        builder.add(RagiumItems.Processors.EMERALD, "エメラルドプロセッサ")
        builder.add(RagiumItems.Processors.NETHER_STAR, "ネザースタープロセッサ")
        builder.add(RagiumItems.Processors.RAGIUM, "ラギウムプロセッサ")

        builder.add(RagiumItems.PressMolds.GEAR, "プレス型（歯車）")
        builder.add(RagiumItems.PressMolds.PIPE, "プレス型（パイプ）")
        builder.add(RagiumItems.PressMolds.PLATE, "プレス型（板材）")
        builder.add(RagiumItems.PressMolds.ROD, "プレス型（棒材）")

        builder.add(RagiumTranslationKeys.ANVIL_DYNAMITE, "着弾点に金床を設置する")
        builder.add(RagiumTranslationKeys.BACKPACK, "同じ色同士でインベントリを共有する")
        builder.add(RagiumTranslationKeys.BEDROCK_DYNAMITE, "着弾したチャンク内の岩盤を整地する")
        builder.add(RagiumTranslationKeys.BLAZING_DYNAMITE, "エンティティやブロックに当たると着火する")
        builder.add(RagiumTranslationKeys.FLATTENING_DYNAMITE, "着弾点より上のブロックを「すべて」消滅させる")
        builder.add(RagiumTranslationKeys.FROSTING_DYNAMITE, "着弾点に粉雪を設置する")
        builder.add(RagiumTranslationKeys.RAGI_WRENCH, "右クリックで水平方向の回転，シフト右クリックで正面を変更")
        builder.add(RagiumTranslationKeys.ROPE, "着弾点からロープを下す")
        builder.add(RagiumTranslationKeys.TRADER_CATALOG, "右クリックで行商人との取引を行う")
        builder.add(
            RagiumTranslationKeys.WARPED_CRYSTAL,
            "右クリックでテレポートアンカーの上にテレポート，シフト右クリックで紐づけ",
        )

        builder.add(RagiumTranslationKeys.DYNAMITE_DESTROY, "地形破壊: %s")
        builder.add(RagiumTranslationKeys.DYNAMITE_POWER, "威力: %s")
        builder.add(RagiumTranslationKeys.FILTER, "搬出機に右クリックで適用，または設定画面を開く")
        builder.add(RagiumTranslationKeys.FILTER_FORMAT, "例: \"minecraft:iron_ingot\", [\"minecraft:water\"], \"#c:ores\"")
        builder.add(RagiumTranslationKeys.RADIOACTIVITY, "放射能レベル: %s")
        builder.add(RagiumTranslationKeys.WARPED_CRYSTAL_DESTINATION, "座標: %s")
        // Item Group
        builder.add(RagiumItemGroup.BUILDING, "Ragium - 建築")
        builder.add(RagiumItemGroup.ITEM, "Ragium - アイテム")
        builder.add(RagiumItemGroup.FLUID, "Ragium - 液体")
        builder.add(RagiumItemGroup.MACHINE, "Ragium - 機械")
        builder.add(RagiumItemGroup.TRANSFER, "Ragium - 輸送")
        // Machine
        builder.add(RagiumTranslationKeys.MACHINE_NAME, "名称: %s")
        builder.add(RagiumTranslationKeys.MACHINE_TIER, "ティア: %s")
        builder.add(RagiumTranslationKeys.MACHINE_NETWORK_ENERGY, "ネットワーク上のエネルギー量: %s ユニット")
        builder.add(RagiumTranslationKeys.MACHINE_RECIPE_COST, "処理コスト: %s E")
        builder.add(RagiumTranslationKeys.MACHINE_SHOW_PREVIEW, "プレビューの表示: %s")

        builder.add(RagiumTranslationKeys.MULTI_SHAPE_ERROR, "次の条件を満たしていません: %s (座標: %s)")
        builder.add(RagiumTranslationKeys.MULTI_SHAPE_SUCCESS, "構造物は有効です！")
        // Machine Tier
        builder.add(HTMachineTier.PRIMITIVE, "簡易", "簡易型%s")
        builder.add(HTMachineTier.BASIC, "基本", "基本型%s")
        builder.add(HTMachineTier.ADVANCED, "発展", "発展型%s")
        // Machine Type
        builder.add(RagiumMachineKeys.BEDROCK_MINER, "岩盤採掘機", "岩盤から鉱物を採掘する")
        builder.add(RagiumMachineKeys.BIOMASS_FERMENTER, "バイオマス発酵槽", "植物からバイオマスを生産する")
        builder.add(RagiumMachineKeys.DRAIN, "排水溝", "正面から液体を，上から経験値を，スロット内の液体キューブから中身を吸い取る")
        builder.add(RagiumMachineKeys.FLUID_DRILL, "液体採掘機", "特定のバイオームから液体を汲み上げる")
        builder.add(RagiumMachineKeys.ROCK_GENERATOR, "岩石生成機", "水と溶岩を少なくとも一つずつ隣接させる")

        builder.add(RagiumMachineKeys.COMBUSTION_GENERATOR, "燃焼発電機", "液体燃料から発電する")
        builder.add(RagiumMachineKeys.NUCLEAR_REACTOR, "原子炉", "放射性燃料から発電する")
        builder.add(RagiumMachineKeys.SOLAR_GENERATOR, "太陽光発電機", "日中に発電する")
        builder.add(RagiumMachineKeys.STEAM_GENERATOR, "蒸気発電機", "水と石炭類から発電する")
        builder.add(RagiumMachineKeys.THERMAL_GENERATOR, "地熱発電機", "高温の液体から発電する")

        builder.add(RagiumMachineKeys.ASSEMBLER, "組立機", "悪魔博士，アッセンブル！")
        builder.add(RagiumMachineKeys.BLAST_FURNACE, "大型高炉", "複数の素材を一つに焼き上げる")
        builder.add(RagiumMachineKeys.CHEMICAL_REACTOR, "化学反応槽", "Are You Ready?")
        builder.add(RagiumMachineKeys.COMPRESSOR, "圧縮機", "saves.zip.zip")
        builder.add(RagiumMachineKeys.CUTTING_MACHINE, "裁断機", "")
        builder.add(RagiumMachineKeys.DISTILLATION_TOWER, "蒸留塔", "原油を処理する")
        builder.add(RagiumMachineKeys.ELECTROLYZER, "電解槽", "エレキ オン")
        builder.add(RagiumMachineKeys.EXTRACTOR, "抽出器", "遠心分離機みたいなやつ")
        builder.add(RagiumMachineKeys.GRINDER, "粉砕機", "クラッシュ・アップ")
        builder.add(RagiumMachineKeys.GROWTH_CHAMBER, "成長チャンバー")
        builder.add(RagiumMachineKeys.INFUSER, "注入機", "遠心分離機じゃないみたいなやつ")
        builder.add(RagiumMachineKeys.LASER_TRANSFORMER, "レーザー変換機")
        builder.add(RagiumMachineKeys.MULTI_SMELTER, "並列精錬機", "複数のアイテムを一度に製錬する")
        builder.add(RagiumMachineKeys.MIXER, "ミキサー", "ゲノミクス...")
        // Material
        builder.add(RagiumMaterialKeys.ALKALI, "アルカリ")
        builder.add(RagiumMaterialKeys.ALUMINUM, "アルミニウム")
        builder.add(RagiumMaterialKeys.ASH, "灰")
        builder.add(RagiumMaterialKeys.BAUXITE, "ボーキサイト")
        builder.add(RagiumMaterialKeys.BRASS, "真鍮")
        builder.add(RagiumMaterialKeys.BRONZE, "青銅")
        builder.add(RagiumMaterialKeys.CINNABAR, "辰砂")
        builder.add(RagiumMaterialKeys.COAL, "石炭")
        builder.add(RagiumMaterialKeys.COPPER, "銅")
        builder.add(RagiumMaterialKeys.CRUDE_RAGINITE, "粗製ラギナイト")
        builder.add(RagiumMaterialKeys.CRYOLITE, "氷晶石")
        builder.add(RagiumMaterialKeys.DEEP_STEEL, "深層鋼")
        builder.add(RagiumMaterialKeys.DIAMOND, "ダイアモンド")
        builder.add(RagiumMaterialKeys.ELECTRUM, "琥珀金")
        builder.add(RagiumMaterialKeys.EMERALD, "エメラルド")
        builder.add(RagiumMaterialKeys.FLUORITE, "蛍石")
        builder.add(RagiumMaterialKeys.GALENA, "方鉛鉱")
        builder.add(RagiumMaterialKeys.GOLD, "金")
        builder.add(RagiumMaterialKeys.INVAR, "不変鋼")
        builder.add(RagiumMaterialKeys.IRIDIUM, "イリジウム")
        builder.add(RagiumMaterialKeys.IRON, "鉄")
        builder.add(RagiumMaterialKeys.LAPIS, "ラピス")
        builder.add(RagiumMaterialKeys.LEAD, "鉛")
        builder.add(RagiumMaterialKeys.NETHER_STAR, "ネザースター")
        builder.add(RagiumMaterialKeys.NETHERITE, "ネザライト")
        builder.add(RagiumMaterialKeys.NICKEL, "ニッケル")
        builder.add(RagiumMaterialKeys.NITER, "硝石")
        builder.add(RagiumMaterialKeys.PERIDOT, "ペリドット")
        builder.add(RagiumMaterialKeys.PLATINUM, "白金")
        builder.add(RagiumMaterialKeys.PLUTONIUM, "プルトニウム")
        builder.add(RagiumMaterialKeys.PYRITE, "黄鉄鉱")
        builder.add(RagiumMaterialKeys.QUARTZ, "水晶")
        builder.add(RagiumMaterialKeys.RAGI_ALLOY, "ラギ合金")
        builder.add(RagiumMaterialKeys.RAGI_CRYSTAL, "ラギクリスタリル")
        builder.add(RagiumMaterialKeys.RAGI_STEEL, "ラギスチール")
        builder.add(RagiumMaterialKeys.RAGINITE, "ラギナイト")
        builder.add(RagiumMaterialKeys.RAGIUM, "ラギウム")
        builder.add(RagiumMaterialKeys.REDSTONE, "レッドストーン")
        builder.add(RagiumMaterialKeys.REFINED_RAGI_STEEL, "精製ラギスチール")
        builder.add(RagiumMaterialKeys.RUBY, "ルビー")
        builder.add(RagiumMaterialKeys.SALT, "塩")
        builder.add(RagiumMaterialKeys.SAPPHIRE, "サファイア")
        builder.add(RagiumMaterialKeys.SILVER, "銀")
        builder.add(RagiumMaterialKeys.SPHALERITE, "閃亜鉛鉱")
        builder.add(RagiumMaterialKeys.STEEL, "スチール")
        builder.add(RagiumMaterialKeys.STONE, "石材")
        builder.add(RagiumMaterialKeys.SULFUR, "硫黄")
        builder.add(RagiumMaterialKeys.TIN, "スズ")
        builder.add(RagiumMaterialKeys.TITANIUM, "チタン")
        builder.add(RagiumMaterialKeys.TUNGSTEN, "タングステン")
        builder.add(RagiumMaterialKeys.URANIUM, "ウラニウム")
        builder.add(RagiumMaterialKeys.WOOD, "木材")
        builder.add(RagiumMaterialKeys.ZINC, "亜鉛")
        // Tag Prefix
        builder.add(HTTagPrefix.DUST, "%sの粉")
        builder.add(HTTagPrefix.GEAR, "%sの歯車")
        builder.add(HTTagPrefix.GEM, "%s")
        builder.add(HTTagPrefix.INGOT, "%sインゴット")
        builder.add(HTTagPrefix.NUGGET, "%sのナゲット")
        builder.add(HTTagPrefix.ORE, "%s鉱石")
        builder.add(HTTagPrefix.PLATE, "%s板")
        builder.add(HTTagPrefix.RAW_MATERIAL, "%sの原石")
        builder.add(HTTagPrefix.ROD, "%s棒")
        builder.add(HTTagPrefix.STORAGE_BLOCK, "%sブロック")
        // World
        builder.addWorld(World.OVERWORLD, "オーバーワールド")
        builder.addWorld(World.NETHER, "ネザー")
        builder.addWorld(World.END, "ジ・エンド")

        generateIntegration(builder)
    }

    private fun generateIntegration(builder: TranslationBuilder) {
        // Config
        builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_COMMON, "一般")
        builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_MACHINE, "機械")
        builder.add(RagiumTranslationKeys.CONFIG_CATEGORY_UTILITY, "ユーティリティ")

        builder.add(RagiumTranslationKeys.CONFIG_HARD_MODE, "ハードモード")
        builder.add(RagiumTranslationKeys.CONFIG_RADIOACTIVE, "放射線によるデバフ")

        builder.add(RagiumTranslationKeys.CONFIG_SHOW_PARTICLE, "パーティクルの表示")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR, "発電機")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_COOLANT, "原子炉の冷却液")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_NITRO, "燃焼発電機のニトロ燃料")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_NON_NITRO, "燃焼発電機の非ニトロ燃料")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_STEAM, "蒸気発電機の水")
        builder.add(RagiumTranslationKeys.CONFIG_GENERATOR_THERMAL, "地熱発電機の液体")

        builder.add(RagiumTranslationKeys.CONFIG_AUTO_ILLUMINATOR, "光源置き太郎の稼働半径")
        builder.add(RagiumTranslationKeys.CONFIG_DYNAMITE_RADIUS, "ダイナマイトによるブロック設置の半径")
        builder.add(RagiumTranslationKeys.CONFIG_DYNAMITE_POWER, "ダイナマイトの威力のデフォルト値")
        builder.add(RagiumTranslationKeys.CONFIG_GIGANT_HAMMER, "ギガントハンマーの採掘速度")
        // Key Binds
        builder.add("category.ragium.ragium", RagiumAPI.MOD_NAME)

        builder.add("key.ragium.open_backpack", "バックパックを開く")
        // Jade
        builder.add(RagiumTranslationKeys.CONFIG_JADE_EXPORTER, "搬出機")
        builder.add(RagiumTranslationKeys.CONFIG_JADE_MACHINE, "機械")
        builder.add(RagiumTranslationKeys.CONFIG_JADE_NETWORK_INTERFACE, "E.N.I")

        builder.add(RagiumTranslationKeys.PROVIDER_JADE_NETWORK_INTERFACE, "エネルギー量: %s E")
        // Patchouli
        builder.add(RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER1, "Tier 1")
        builder.add(RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER10, "最初のステージ")
        builder.add(RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER2, "Tier 2")
        builder.add(RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER20, "次なるステージ")
        builder.add(RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER3, "Tier 3")
        builder.add(RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER30, "さらなるステージ")
        builder.add(RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER4, "Tier 4")
        builder.add(RagiumTranslationKeys.PATCHOULI_CATEGORY_TIER40, "最後のステージ")

        builder.add(RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE, "粗製ラギナイトを見つけよう")
        builder.add(
            RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE0,
            "オーバーワールドのy = [-48, 48]の範囲に生成される",
        )
        builder.add(RagiumTranslationKeys.PATCHOULI_CRUDE_RAGINITE_ORE1, "ラギナイト鉱石から[1, 3]個ドロップする")

        builder.add(RagiumTranslationKeys.PATCHOULI_RAGI_ALLOY, "ラギ合金を作ろう")
        builder.add(RagiumTranslationKeys.PATCHOULI_RAGI_ALLOY0, "ラギ合金はTier 1のメイン金属となる")
        // REI
        builder.add(RagiumTranslationKeys.REI_ENTRY_NO_MATCHING, "次を満たす値がありません: %s")
        builder.add(RagiumTranslationKeys.REI_ENTRY_APPLY_DAMAGE, "実行時に%sだけ耐久値を減らす")

        builder.add(RagiumTranslationKeys.REI_RECIPE_BIOME, "次のバイオームで見つかる: %s")
        builder.add(RagiumTranslationKeys.REI_RECIPE_INFO, "レシピ情報")
    }
}
