package com.gokousei.fgo_tools;

import java.util.List;

public class ServantDataBean {

    private List<List<ServantBean>> servant;
    private List<?> enemy;

    public List<List<ServantBean>> getServant() {
        return servant;
    }

    public void setServant(List<List<ServantBean>> servant) {
        this.servant = servant;
    }

    public List<?> getEnemy() {
        return enemy;
    }

    public void setEnemy(List<?> enemy) {
        this.enemy = enemy;
    }

    public static class ServantBean {
        /**
         * Summary : {"no":"001","rarity":"★★★\n★★★★","name":"マシュ・キリエライト\n瑪琇・基利艾拉特","servantClass":"Shielder","attribute":"地","hp":"1854 / 10302\n2060 / 11516","atk":"1261 / 6791\n1455 / 7815","illustrator":"武内崇","cv":"高橋李依","faction":"秩序・善","gender":"女性"}
         * Property : {"CommandCard":{"arts":["2張","2Hits"],"buster":["2張","1Hit"],"quick":["1張","2Hits"],"extra":["1張","3Hits"],"noblePhantasm":["1張","1Hits"]},"Np":{"arts":"0.84%","buster":"0.84%","quick":"0.84%","extra":"0.84%","noblePhantasm":"0.84%","defense":"3%"},"Other":{"startRate":"9.9%","deathRate":"24.5%","startWeights":"99"}}
         * ServantSkills : {"servantSkillOne":[["今は脆き雪花の壁","冷卻7回合","初始","我方全体防御力提升[Lv.](3回合)","10% 10.5% 11% 11.5% 12% 12.5% 13% 13.5% 14% 15%","skill_defenseUp"],["誉れ堅き雪花の壁","冷卻7回合","灵基再临第3阶段 通过关卡神王オジマンディアス(1/3)","我方全体防御力提升[Lv.](3回合)","15% 15.5% 16% 16.5% 17% 17.5% 18% 18.5% 19% 20%","赋予伤害减免状态(1次)","2000","skill_defenseUp"]],"servantSkillTwo":["時に煙る白亜の壁","冷卻9回合","初始","對我方單體賦予無敵狀態(1回合)","NP增加[Lv.]","10% 11% 12% 13% 14% 15% 16% 17% 18% 20%","skill_absoluteDefense"],"servantSkillThree":["奮い断つ決意の盾","冷卻8回合","靈基再臨第2階段","對自身賦予目標集中狀態(1回合)","100% 120% 140% 160% 180% 200% 220% 240% 260% 300%","NP獲得量大提升[Lv.](1回合)","200% 220% 240% 260% 280% 300% 320% 340% 360% 400%","skill_taunt"]}
         * ClassSkills : {"classSkillOne":["対魔力 A","自身的弱體耐性提升：20%","skill_magicDefense"],"classSkillTwo":["騎乗 C","自身的Quick卡性能稍微提升：6%","skill_riding"]}
         * NoblePhantasm : [["ロード・カルデアス","仮想宝具 疑似展開／人理の礎","D","對人寶具","マシュの特訓","－","我方全體的防禦力提升(3回合)\n<OverCharge的話效果UP>","30% 35% 40% 45% 50%","賦予傷害減免狀態[Lv.](3回合)","100 550 775 888 1000"],["ロード・キャメロット","いまは遙か理想の城 ","B+++","對惡寶具","レプリカ(4/5)","－","我方全體的防禦力提升(3回合)\n<OverCharge的話效果UP>","30% 35% 40% 45% 50%","賦予傷害減免狀態[Lv.](3回合)","100 550 775 888 1000","自身除外的我方全體攻擊力提升(3回合)","30%"]]
         */

        private SummaryBean Summary;
        private PropertyBean Property;
        private ServantSkillsBean ServantSkills;
        private ClassSkillsBean ClassSkills;
        private List<List<String>> NoblePhantasm;

        public SummaryBean getSummary() {
            return Summary;
        }

        public void setSummary(SummaryBean Summary) {
            this.Summary = Summary;
        }

        public PropertyBean getProperty() {
            return Property;
        }

        public void setProperty(PropertyBean Property) {
            this.Property = Property;
        }

        public ServantSkillsBean getServantSkills() {
            return ServantSkills;
        }

        public void setServantSkills(ServantSkillsBean ServantSkills) {
            this.ServantSkills = ServantSkills;
        }

        public ClassSkillsBean getClassSkills() {
            return ClassSkills;
        }

        public void setClassSkills(ClassSkillsBean ClassSkills) {
            this.ClassSkills = ClassSkills;
        }

        public List<List<String>> getNoblePhantasm() {
            return NoblePhantasm;
        }

        public void setNoblePhantasm(List<List<String>> NoblePhantasm) {
            this.NoblePhantasm = NoblePhantasm;
        }

        public static class SummaryBean {
            /**
             * no : 001
             * rarity : ★★★
             ★★★★
             * name : マシュ・キリエライト
             瑪琇・基利艾拉特
             * servantClass : Shielder
             * attribute : 地
             * hp : 1854 / 10302
             2060 / 11516
             * atk : 1261 / 6791
             1455 / 7815
             * illustrator : 武内崇
             * cv : 高橋李依
             * faction : 秩序・善
             * gender : 女性
             */

            private String no;
            private String rarity;
            private String name;
            private String servantClass;
            private String attribute;
            private String hp;
            private String atk;
            private String illustrator;
            private String cv;
            private String faction;
            private String gender;

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getRarity() {
                return rarity;
            }

            public void setRarity(String rarity) {
                this.rarity = rarity;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getServantClass() {
                return servantClass;
            }

            public void setServantClass(String servantClass) {
                this.servantClass = servantClass;
            }

            public String getAttribute() {
                return attribute;
            }

            public void setAttribute(String attribute) {
                this.attribute = attribute;
            }

            public String getHp() {
                return hp;
            }

            public void setHp(String hp) {
                this.hp = hp;
            }

            public String getAtk() {
                return atk;
            }

            public void setAtk(String atk) {
                this.atk = atk;
            }

            public String getIllustrator() {
                return illustrator;
            }

            public void setIllustrator(String illustrator) {
                this.illustrator = illustrator;
            }

            public String getCv() {
                return cv;
            }

            public void setCv(String cv) {
                this.cv = cv;
            }

            public String getFaction() {
                return faction;
            }

            public void setFaction(String faction) {
                this.faction = faction;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }

        public static class PropertyBean {
            /**
             * CommandCard : {"arts":["2張","2Hits"],"buster":["2張","1Hit"],"quick":["1張","2Hits"],"extra":["1張","3Hits"],"noblePhantasm":["1張","1Hits"]}
             * Np : {"arts":"0.84%","buster":"0.84%","quick":"0.84%","extra":"0.84%","noblePhantasm":"0.84%","defense":"3%"}
             * Other : {"startRate":"9.9%","deathRate":"24.5%","startWeights":"99"}
             */

            private CommandCardBean CommandCard;
            private NpBean Np;
            private OtherBean Other;

            public CommandCardBean getCommandCard() {
                return CommandCard;
            }

            public void setCommandCard(CommandCardBean CommandCard) {
                this.CommandCard = CommandCard;
            }

            public NpBean getNp() {
                return Np;
            }

            public void setNp(NpBean Np) {
                this.Np = Np;
            }

            public OtherBean getOther() {
                return Other;
            }

            public void setOther(OtherBean Other) {
                this.Other = Other;
            }

            public static class CommandCardBean {
                private List<String> arts;
                private List<String> buster;
                private List<String> quick;
                private List<String> extra;
                private List<String> noblePhantasm;

                public List<String> getArts() {
                    return arts;
                }

                public void setArts(List<String> arts) {
                    this.arts = arts;
                }

                public List<String> getBuster() {
                    return buster;
                }

                public void setBuster(List<String> buster) {
                    this.buster = buster;
                }

                public List<String> getQuick() {
                    return quick;
                }

                public void setQuick(List<String> quick) {
                    this.quick = quick;
                }

                public List<String> getExtra() {
                    return extra;
                }

                public void setExtra(List<String> extra) {
                    this.extra = extra;
                }

                public List<String> getNoblePhantasm() {
                    return noblePhantasm;
                }

                public void setNoblePhantasm(List<String> noblePhantasm) {
                    this.noblePhantasm = noblePhantasm;
                }
            }

            public static class NpBean {
                /**
                 * arts : 0.84%
                 * buster : 0.84%
                 * quick : 0.84%
                 * extra : 0.84%
                 * noblePhantasm : 0.84%
                 * defense : 3%
                 */

                private String arts;
                private String buster;
                private String quick;
                private String extra;
                private String noblePhantasm;
                private String defense;

                public String getArts() {
                    return arts;
                }

                public void setArts(String arts) {
                    this.arts = arts;
                }

                public String getBuster() {
                    return buster;
                }

                public void setBuster(String buster) {
                    this.buster = buster;
                }

                public String getQuick() {
                    return quick;
                }

                public void setQuick(String quick) {
                    this.quick = quick;
                }

                public String getExtra() {
                    return extra;
                }

                public void setExtra(String extra) {
                    this.extra = extra;
                }

                public String getNoblePhantasm() {
                    return noblePhantasm;
                }

                public void setNoblePhantasm(String noblePhantasm) {
                    this.noblePhantasm = noblePhantasm;
                }

                public String getDefense() {
                    return defense;
                }

                public void setDefense(String defense) {
                    this.defense = defense;
                }
            }

            public static class OtherBean {
                /**
                 * startRate : 9.9%
                 * deathRate : 24.5%
                 * startWeights : 99
                 */

                private String startRate;
                private String deathRate;
                private String startWeights;

                public String getStartRate() {
                    return startRate;
                }

                public void setStartRate(String startRate) {
                    this.startRate = startRate;
                }

                public String getDeathRate() {
                    return deathRate;
                }

                public void setDeathRate(String deathRate) {
                    this.deathRate = deathRate;
                }

                public String getStartWeights() {
                    return startWeights;
                }

                public void setStartWeights(String startWeights) {
                    this.startWeights = startWeights;
                }
            }
        }

        public static class ServantSkillsBean {
            private List<List<String>> servantSkillOne;
            private List<String> servantSkillTwo;
            private List<String> servantSkillThree;

            public List<List<String>> getServantSkillOne() {
                return servantSkillOne;
            }

            public void setServantSkillOne(List<List<String>> servantSkillOne) {
                this.servantSkillOne = servantSkillOne;
            }

            public List<String> getServantSkillTwo() {
                return servantSkillTwo;
            }

            public void setServantSkillTwo(List<String> servantSkillTwo) {
                this.servantSkillTwo = servantSkillTwo;
            }

            public List<String> getServantSkillThree() {
                return servantSkillThree;
            }

            public void setServantSkillThree(List<String> servantSkillThree) {
                this.servantSkillThree = servantSkillThree;
            }
        }

        public static class ClassSkillsBean {
            private List<String> classSkillOne;
            private List<String> classSkillTwo;

            public List<String> getClassSkillOne() {
                return classSkillOne;
            }

            public void setClassSkillOne(List<String> classSkillOne) {
                this.classSkillOne = classSkillOne;
            }

            public List<String> getClassSkillTwo() {
                return classSkillTwo;
            }

            public void setClassSkillTwo(List<String> classSkillTwo) {
                this.classSkillTwo = classSkillTwo;
            }
        }
    }
}
