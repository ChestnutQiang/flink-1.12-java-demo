/*
 * ====
 *     ====
 *         #set( $symbol_pound = '#' )
 *         #set( $symbol_dollar = '$' )
 *         #set( $symbol_escape = '\' )
 *
 *                                          Apache License
 *                                    Version 2.0, January 2004
 *                                 http://www.apache.org/licenses/
 *
 *            TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 *
 *            1. Definitions.
 *
 *               "License" shall mean the terms and conditions for use, reproduction,
 *               and distribution as defined by Sections 1 through 9 of this document.
 *
 *               "Licensor" shall mean the copyright owner or entity authorized by
 *               the copyright owner that is granting the License.
 *
 *               "Legal Entity" shall mean the union of the acting entity and all
 *               other entities that control, are controlled by, or are under common
 *               control with that entity. For the purposes of this definition,
 *               "control" means (i) the power, direct or indirect, to cause the
 *               direction or management of such entity, whether by contract or
 *               otherwise, or (ii) ownership of fifty percent (50%) or more of the
 *               outstanding shares, or (iii) beneficial ownership of such entity.
 *
 *               "You" (or "Your") shall mean an individual or Legal Entity
 *               exercising permissions granted by this License.
 *
 *               "Source" form shall mean the preferred form for making modifications,
 *               including but not limited to software source code, documentation
 *               source, and configuration files.
 *
 *               "Object" form shall mean any form resulting from mechanical
 *               transformation or translation of a Source form, including but
 *               not limited to compiled object code, generated documentation,
 *               and conversions to other media types.
 *
 *               "Work" shall mean the work of authorship, whether in Source or
 *               Object form, made available under the License, as indicated by a
 *               copyright notice that is included in or attached to the work
 *               (an example is provided in the Appendix below).
 *
 *               "Derivative Works" shall mean any work, whether in Source or Object
 *               form, that is based on (or derived from) the Work and for which the
 *               editorial revisions, annotations, elaborations, or other modifications
 *               represent, as a whole, an original work of authorship. For the purposes
 *               of this License, Derivative Works shall not include works that remain
 *               separable from, or merely link (or bind by name) to the interfaces of,
 *               the Work and Derivative Works thereof.
 *
 *               "Contribution" shall mean any work of authorship, including
 *               the original version of the Work and any modifications or additions
 *               to that Work or Derivative Works thereof, that is intentionally
 *               submitted to Licensor for inclusion in the Work by the copyright owner
 *               or by an individual or Legal Entity authorized to submit on behalf of
 *               the copyright owner. For the purposes of this definition, "submitted"
 *               means any form of electronic, verbal, or written communication sent
 *               to the Licensor or its representatives, including but not limited to
 *               communication on electronic mailing lists, source code control systems,
 *               and issue tracking systems that are managed by, or on behalf of, the
 *               Licensor for the purpose of discussing and improving the Work, but
 *               excluding communication that is conspicuously marked or otherwise
 *               designated in writing by the copyright owner as "Not a Contribution."
 *
 *               "Contributor" shall mean Licensor and any individual or Legal Entity
 *               on behalf of whom a Contribution has been received by Licensor and
 *               subsequently incorporated within the Work.
 *
 *            2. Grant of Copyright License. Subject to the terms and conditions of
 *               this License, each Contributor hereby grants to You a perpetual,
 *               worldwide, non-exclusive, no-charge, royalty-free, irrevocable
 *               copyright license to reproduce, prepare Derivative Works of,
 *               publicly display, publicly perform, sublicense, and distribute the
 *               Work and such Derivative Works in Source or Object form.
 *
 *            3. Grant of Patent License. Subject to the terms and conditions of
 *               this License, each Contributor hereby grants to You a perpetual,
 *               worldwide, non-exclusive, no-charge, royalty-free, irrevocable
 *               (except as stated in this section) patent license to make, have made,
 *               use, offer to sell, sell, import, and otherwise transfer the Work,
 *               where such license applies only to those patent claims licensable
 *               by such Contributor that are necessarily infringed by their
 *               Contribution(s) alone or by combination of their Contribution(s)
 *               with the Work to which such Contribution(s) was submitted. If You
 *               institute patent litigation against any entity (including a
 *               cross-claim or counterclaim in a lawsuit) alleging that the Work
 *               or a Contribution incorporated within the Work constitutes direct
 *               or contributory patent infringement, then any patent licenses
 *               granted to You under this License for that Work shall terminate
 *               as of the date such litigation is filed.
 *
 *            4. Redistribution. You may reproduce and distribute copies of the
 *               Work or Derivative Works thereof in any medium, with or without
 *               modifications, and in Source or Object form, provided that You
 *               meet the following conditions:
 *
 *               (a) You must give any other recipients of the Work or
 *                   Derivative Works a copy of this License; and
 *
 *               (b) You must cause any modified files to carry prominent notices
 *                   stating that You changed the files; and
 *
 *               (c) You must retain, in the Source form of any Derivative Works
 *                   that You distribute, all copyright, patent, trademark, and
 *                   attribution notices from the Source form of the Work,
 *                   excluding those notices that do not pertain to any part of
 *                   the Derivative Works; and
 *
 *               (d) If the Work includes a "NOTICE" text file as part of its
 *                   distribution, then any Derivative Works that You distribute must
 *                   include a readable copy of the attribution notices contained
 *                   within such NOTICE file, excluding those notices that do not
 *                   pertain to any part of the Derivative Works, in at least one
 *                   of the following places: within a NOTICE text file distributed
 *                   as part of the Derivative Works; within the Source form or
 *                   documentation, if provided along with the Derivative Works; or,
 *                   within a display generated by the Derivative Works, if and
 *                   wherever such third-party notices normally appear. The contents
 *                   of the NOTICE file are for informational purposes only and
 *                   do not modify the License. You may add Your own attribution
 *                   notices within Derivative Works that You distribute, alongside
 *                   or as an addendum to the NOTICE text from the Work, provided
 *                   that such additional attribution notices cannot be construed
 *                   as modifying the License.
 *
 *               You may add Your own copyright statement to Your modifications and
 *               may provide additional or different license terms and conditions
 *               for use, reproduction, or distribution of Your modifications, or
 *               for any such Derivative Works as a whole, provided Your use,
 *               reproduction, and distribution of the Work otherwise complies with
 *               the conditions stated in this License.
 *
 *            5. Submission of Contributions. Unless You explicitly state otherwise,
 *               any Contribution intentionally submitted for inclusion in the Work
 *               by You to the Licensor shall be under the terms and conditions of
 *               this License, without any additional terms or conditions.
 *               Notwithstanding the above, nothing herein shall supersede or modify
 *               the terms of any separate license agreement you may have executed
 *               with Licensor regarding such Contributions.
 *
 *            6. Trademarks. This License does not grant permission to use the trade
 *               names, trademarks, service marks, or product names of the Licensor,
 *               except as required for reasonable and customary use in describing the
 *               origin of the Work and reproducing the content of the NOTICE file.
 *
 *            7. Disclaimer of Warranty. Unless required by applicable law or
 *               agreed to in writing, Licensor provides the Work (and each
 *               Contributor provides its Contributions) on an "AS IS" BASIS,
 *               WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *               implied, including, without limitation, any warranties or conditions
 *               of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A
 *               PARTICULAR PURPOSE. You are solely responsible for determining the
 *               appropriateness of using or redistributing the Work and assume any
 *               risks associated with Your exercise of permissions under this License.
 *
 *            8. Limitation of Liability. In no event and under no legal theory,
 *               whether in tort (including negligence), contract, or otherwise,
 *               unless required by applicable law (such as deliberate and grossly
 *               negligent acts) or agreed to in writing, shall any Contributor be
 *               liable to You for damages, including any direct, indirect, special,
 *               incidental, or consequential damages of any character arising as a
 *               result of this License or out of the use or inability to use the
 *               Work (including but not limited to damages for loss of goodwill,
 *               work stoppage, computer failure or malfunction, or any and all
 *               other commercial damages or losses), even if such Contributor
 *               has been advised of the possibility of such damages.
 *
 *            9. Accepting Warranty or Additional Liability. While redistributing
 *               the Work or Derivative Works thereof, You may choose to offer,
 *               and charge a fee for, acceptance of support, warranty, indemnity,
 *               or other liability obligations and/or rights consistent with this
 *               License. However, in accepting such obligations, You may act only
 *               on Your own behalf and on Your sole responsibility, not on behalf
 *               of any other Contributor, and only if You agree to indemnify,
 *               defend, and hold each Contributor harmless for any liability
 *               incurred by, or claims asserted against, such Contributor by reason
 *               of your accepting any such warranty or additional liability.
 *
 *            END OF TERMS AND CONDITIONS
 *
 *            APPENDIX: How to apply the Apache License to your work.
 *
 *               To apply the Apache License to your work, attach the following
 *               boilerplate notice, with the fields enclosed by brackets "[]"
 *               replaced with your own identifying information. (Don't include
 *               the brackets!)  The text should be enclosed in the appropriate
 *               comment syntax for the file format. We also recommend that a
 *               file or class name and description of purpose be included on the
 *               same "printed page" as the copyright notice for easier
 *               identification within third-party archives.
 *
 *            Copyright 2016 ShenHuaJie iBase4J@163.com
 *
 *            Licensed under the Apache License, Version 2.0 (the "License");
 *            you may not use this file except in compliance with the License.
 *            You may obtain a copy of the License at
 *
 *                http://www.apache.org/licenses/LICENSE-2.0
 *
 *            Unless required by applicable law or agreed to in writing, software
 *            distributed under the License is distributed on an "AS IS" BASIS,
 *            WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *            See the License for the specific language governing permissions and
 *            limitations under the License.
 *
 *         ======================================================================
 *
 *                                                 Apache许可证
 *                                             版本 2.0，2004年1月
 *                                         http://www.apache.org/licenses/
 *
 *            使用、重生成及分发的术语和条件：
 *
 *            1.定义
 *
 *              "许可证"是指根据本文档第1到第9部分关于使用、重生成和分发的术语和条件。
 *
 *              "许可证颁发者"是指版权所有者或者由版权所有者批准的授权许可证的实体。
 *
 *              "法律实体"是指实施实体和进行控制的所有其它实体受该实体控制，或者受该实体集中控制。
 *              根据此定义，"控制"是指(i)让无论是否签订协议的上述实体，进行指导或管理的直接权利或间接权利，
 *              或者(ii)拥有百分之五十(50%)或以上已发行股票的所有者，或者(iii)上述实体的实权所有者。
 *
 *              "用户"(或"用户的")是指行使本许可证所授予权限的个人或法律实体。
 *
 *              "源程序"形式是指对包含但不限制软件源代码、文档源程序和配置文件进行修改的首选形式。
 *
 *              "目标"形式是指对源程序形式进行机械转换或翻译的任何形式，包括但不限于对编译的目标代码，
 *              生成的文件以及转换为其它媒体类型。
 *
 *              "作品"是指根据本许可证所制作的源程序形式或目标形式的著作，在著作中包含的或附加的版权通知
 *              (在下面附录中提供了一个示例)。
 *
 *              "衍生作品"是指基于作品(或从作品衍生而来)的源程序形式或目标形式的任何作品，以及编辑修订、
 *              注释、详细描述或其它修订等构成原创著作作品的整体。根据本许可证，衍生作品不得包括与作品及其
 *              衍生作品分离之作品，或仅与作品及其衍生作品的接口相链接(或按名称结合)之作品。
 *
 *              "贡献"是指任何著作作品，包括作品的原始版本和对该作品或衍生作品所做的任何修订或补充，
 *              意在提交给许可证颁发者以让版权所有者或代表版权所有者的授权个人或法律实体包含在其作品中。
 *              根据此定义，"提交"一词表示发送给许可证颁发者或其代表人，任何电子的、口头的或书面的交流信息形式，
 *              包括但不限于在由许可证颁发者或者代表其管理的电子邮件清单、源代码控制系统、以及发布跟踪系统上为
 *              讨论和提高作品的交流，但不包括由版权所有者以书面形式明显标注或指定为"非贡献"的交流活动。
 *
 *              "贡献者"是指许可证颁发者和代表从许可证颁发者接受之贡献的并随后包含在作品之贡献中的任何个人或法律实体。
 *
 *            2.版权许可证的授予
 *
 *              根据本许可证的条款，每个贡献者授予用户永久性的、全球性的、非专有性的、免费的、无版权费的、
 *              不可撤销的版权许可证以源程序形式或目标形式复制、准备衍生作品、公开显示、公开执行、
 *              授予分许可证、以及分发作品和这样的衍生作品。
 *
 *            3.专利许可证的授予
 *
 *              根据本许可证的条款，每个贡献者授予用户永久性的、全球性的、非专有性的、免费的、无版权费的、
 *              不可撤销的(除在本部分进行说明)专利许可证对作品进行制作、让人制作、使用、提供销售、销售、
 *              进口和其它转让，且这样的许可证仅适用于在所递交作品的贡献中因可由单一的或多个这样的贡献者
 *              授予而必须侵犯的申请专利。如果用户对任何实体针对作品或作品中所涉及贡献提出因直接性或贡献性
 *              专利侵权而提起专利法律诉讼(包括交互诉讼请求或反索赔)，那么根据本许可证，授予用户针对作品
 *              的任何专利许可证将在提起上述诉讼之日起终止。
 *
 *            4.重新分发
 *
 *              用户可在任何媒介中复制和分发作品或衍生作品之副本，无论是否修订，还是以源程序形式或目标形式，
 *              条件是用户需满足下列条款：
 *
 *              a) 用户必须为作品或衍生作品的任何其他接收者提供本许可证的副本；并且
 *
 *              b) 用户必须让任何修改过的文件附带明显的通知，声明用户已更改文件；并且
 *
 *              c) 用户必须从作品的源程序形式中保留衍生作品源程序形式的用户所分发的所有版权、专利、
 *                 商标和属性通知，但不包括不属于衍生作品任何部分的类似通知；并且
 *
 *              d) 如果作品将"通知"文本文件包括为其分发作品的一部分，那么用户分发的任何衍生作品中须至少
 *                 在下列地方之一包括，在这样的通知文件中所包含的属性通知的可读副本，但不包括那些不属于衍生
 *         	    作品任何部分的通知：在作为衍生作品一部分而分发的通知文本文件中；如果与衍生作品一起提供则
 *         	    在源程序形式或文件中；或者通常作为第三方通知出现的时候和地方，在衍生作品中产生的画面中。
 *         	    通知文件的内容仅供信息提供，并未对许可证进行修改。用户可在其分发的衍生作品中在作品的通知
 *         	    文本后或作为附录添加自己的属性通知，条件是附加的属性通知不得构成修改本许可证。
 *
 *              用户可以为自身所做出的修订添加自己的版权声明并可对自身所做出修订内容或为这样的衍生作品作为
 *              整体的使用、复制或分发提供附加或不同的条款，条件是用户对作品的使用、复制和分发必须符合本许
 *              可证中声明的条款。
 *
 *            5.贡献的提交。
 *
 *              除非用户明确声明，在作品中由用户向许可证颁发者的提交若要包含在贡献中，必须在无任何附加条款下
 *              符合本许可证的条款。尽管上面如此规定，执行许可证颁发者有关贡献的条款时，任何情况下均不得替代
 *              或修改任何单独许可证协议的条款。
 *
 *            6.商标。本许可证并未授予用户使用许可证颁发者的商号、商标、服务标记或产品名称，除非将这些名称
 *              用于合理性和惯例性描述作品起源和复制通知文件的内容时。
 *
 *            7.保证否认条款。除非因适用法律需要或书面同意，许可证颁发者以"按原样"基础提供作品(并且每个
 *              贡献者提供其贡献)，无任何明示的或暗示的保证或条件，包括但不限于关于所有权、不侵权、
 *         	 商品适销性、或适用性的保证或条件。用户仅对使用或重新分发作品的正确性负责，并需承担根据本
 *         	 许可证行使权限时的任何风险。
 *
 *            8.责任限制条款。在任何情况下并根据任何法律，无论是因侵权(包括过失)或根据合同，还是其它原因，
 *              除非根据适用法律需要(例如故意行为和重大过失行为)或经书面同意，即使贡献者事先已被告知发生
 *              损害的可能性，任何贡献者不就用户因使用本许可证或不能使用或无法使用作品(包括但不限于商誉损失、
 *              停工、计算机失效或故障，或任何商业损坏或损失)而造成的损失，包括直接的、非直接的、特殊的、意外
 *              的或间接的字符损坏而负责。
 *
 *            9.接受保证或附加责任。重新分发作品或及其衍生作品时，用户可选择提供或为符合本许可证承担之支持、
 *              担保、赔偿或其它职责义务和/或权利而收取费用。但是，在承担上述义务时，用户只可代表用户本身和
 *              用户本身责任来执行，无需代表任何其它贡献者，并且用户仅可保证、防护并保持每个贡献者不受任何
 *              因此而产生的责任或对因用户自身承担这样的保证或附加责任而对这样的贡献者所提出的索赔。
 *
 *            条款结束
 *
 *            附录：如何向用户作品中应用Apache许可证。
 *
 *            若要向用户作品应用Apache许可证，请附加下列样本通知，将括号"[]"中的字段以用户自身的
 *            区分信息来替换(但不包括括号)。文本必须以文件格式适当的注释句法包含在其中。
 *            另外建议将文件名或类别名以及目的说明包含在相同的"打印页"上作为版权通知，以更加容易的区分出第三方档案。
 *
 *            版权所有 2016 ShenHuaJie iBase4J@163.com 根据2.0版本Apache许可证("许可证")授权；
 *            根据本许可证，用户可以不使用此文件。
 *
 *            用户可从下列网址获得许可证副本：http://www.apache.org/licenses/LICENSE-2.0
 *            除非因适用法律需要或书面同意，根据许可证分发的软件是基于"按原样"基础提供，
 *            无任何明示的或暗示的保证或条件。详见根据许可证许可下，特定语言的管辖权限和限制。
 *
 *            =======================================================
 *
 *            简要解释：
 *
 *            1.需要给代码的用户一份Apache Licence
 *            2.如果你修改了代码，需要在被修改的文件中说明。
 *            3.在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，
 *              专利声明和其他原来作者规定需要包含的说明。
 *            4.如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有 Apache Licence。
 *              你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。
 *     ====
 *
 *     #set( $symbol_pound = '#' )
 *     #set( $symbol_dollar = '$' )
 *     #set( $symbol_escape = '\' )
 *
 *                                      Apache License
 *                                Version 2.0, January 2004
 *                             http://www.apache.org/licenses/
 *
 *        TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 *
 *        1. Definitions.
 *
 *           "License" shall mean the terms and conditions for use, reproduction,
 *           and distribution as defined by Sections 1 through 9 of this document.
 *
 *           "Licensor" shall mean the copyright owner or entity authorized by
 *           the copyright owner that is granting the License.
 *
 *           "Legal Entity" shall mean the union of the acting entity and all
 *           other entities that control, are controlled by, or are under common
 *           control with that entity. For the purposes of this definition,
 *           "control" means (i) the power, direct or indirect, to cause the
 *           direction or management of such entity, whether by contract or
 *           otherwise, or (ii) ownership of fifty percent (50%) or more of the
 *           outstanding shares, or (iii) beneficial ownership of such entity.
 *
 *           "You" (or "Your") shall mean an individual or Legal Entity
 *           exercising permissions granted by this License.
 *
 *           "Source" form shall mean the preferred form for making modifications,
 *           including but not limited to software source code, documentation
 *           source, and configuration files.
 *
 *           "Object" form shall mean any form resulting from mechanical
 *           transformation or translation of a Source form, including but
 *           not limited to compiled object code, generated documentation,
 *           and conversions to other media types.
 *
 *           "Work" shall mean the work of authorship, whether in Source or
 *           Object form, made available under the License, as indicated by a
 *           copyright notice that is included in or attached to the work
 *           (an example is provided in the Appendix below).
 *
 *           "Derivative Works" shall mean any work, whether in Source or Object
 *           form, that is based on (or derived from) the Work and for which the
 *           editorial revisions, annotations, elaborations, or other modifications
 *           represent, as a whole, an original work of authorship. For the purposes
 *           of this License, Derivative Works shall not include works that remain
 *           separable from, or merely link (or bind by name) to the interfaces of,
 *           the Work and Derivative Works thereof.
 *
 *           "Contribution" shall mean any work of authorship, including
 *           the original version of the Work and any modifications or additions
 *           to that Work or Derivative Works thereof, that is intentionally
 *           submitted to Licensor for inclusion in the Work by the copyright owner
 *           or by an individual or Legal Entity authorized to submit on behalf of
 *           the copyright owner. For the purposes of this definition, "submitted"
 *           means any form of electronic, verbal, or written communication sent
 *           to the Licensor or its representatives, including but not limited to
 *           communication on electronic mailing lists, source code control systems,
 *           and issue tracking systems that are managed by, or on behalf of, the
 *           Licensor for the purpose of discussing and improving the Work, but
 *           excluding communication that is conspicuously marked or otherwise
 *           designated in writing by the copyright owner as "Not a Contribution."
 *
 *           "Contributor" shall mean Licensor and any individual or Legal Entity
 *           on behalf of whom a Contribution has been received by Licensor and
 *           subsequently incorporated within the Work.
 *
 *        2. Grant of Copyright License. Subject to the terms and conditions of
 *           this License, each Contributor hereby grants to You a perpetual,
 *           worldwide, non-exclusive, no-charge, royalty-free, irrevocable
 *           copyright license to reproduce, prepare Derivative Works of,
 *           publicly display, publicly perform, sublicense, and distribute the
 *           Work and such Derivative Works in Source or Object form.
 *
 *        3. Grant of Patent License. Subject to the terms and conditions of
 *           this License, each Contributor hereby grants to You a perpetual,
 *           worldwide, non-exclusive, no-charge, royalty-free, irrevocable
 *           (except as stated in this section) patent license to make, have made,
 *           use, offer to sell, sell, import, and otherwise transfer the Work,
 *           where such license applies only to those patent claims licensable
 *           by such Contributor that are necessarily infringed by their
 *           Contribution(s) alone or by combination of their Contribution(s)
 *           with the Work to which such Contribution(s) was submitted. If You
 *           institute patent litigation against any entity (including a
 *           cross-claim or counterclaim in a lawsuit) alleging that the Work
 *           or a Contribution incorporated within the Work constitutes direct
 *           or contributory patent infringement, then any patent licenses
 *           granted to You under this License for that Work shall terminate
 *           as of the date such litigation is filed.
 *
 *        4. Redistribution. You may reproduce and distribute copies of the
 *           Work or Derivative Works thereof in any medium, with or without
 *           modifications, and in Source or Object form, provided that You
 *           meet the following conditions:
 *
 *           (a) You must give any other recipients of the Work or
 *               Derivative Works a copy of this License; and
 *
 *           (b) You must cause any modified files to carry prominent notices
 *               stating that You changed the files; and
 *
 *           (c) You must retain, in the Source form of any Derivative Works
 *               that You distribute, all copyright, patent, trademark, and
 *               attribution notices from the Source form of the Work,
 *               excluding those notices that do not pertain to any part of
 *               the Derivative Works; and
 *
 *           (d) If the Work includes a "NOTICE" text file as part of its
 *               distribution, then any Derivative Works that You distribute must
 *               include a readable copy of the attribution notices contained
 *               within such NOTICE file, excluding those notices that do not
 *               pertain to any part of the Derivative Works, in at least one
 *               of the following places: within a NOTICE text file distributed
 *               as part of the Derivative Works; within the Source form or
 *               documentation, if provided along with the Derivative Works; or,
 *               within a display generated by the Derivative Works, if and
 *               wherever such third-party notices normally appear. The contents
 *               of the NOTICE file are for informational purposes only and
 *               do not modify the License. You may add Your own attribution
 *               notices within Derivative Works that You distribute, alongside
 *               or as an addendum to the NOTICE text from the Work, provided
 *               that such additional attribution notices cannot be construed
 *               as modifying the License.
 *
 *           You may add Your own copyright statement to Your modifications and
 *           may provide additional or different license terms and conditions
 *           for use, reproduction, or distribution of Your modifications, or
 *           for any such Derivative Works as a whole, provided Your use,
 *           reproduction, and distribution of the Work otherwise complies with
 *           the conditions stated in this License.
 *
 *        5. Submission of Contributions. Unless You explicitly state otherwise,
 *           any Contribution intentionally submitted for inclusion in the Work
 *           by You to the Licensor shall be under the terms and conditions of
 *           this License, without any additional terms or conditions.
 *           Notwithstanding the above, nothing herein shall supersede or modify
 *           the terms of any separate license agreement you may have executed
 *           with Licensor regarding such Contributions.
 *
 *        6. Trademarks. This License does not grant permission to use the trade
 *           names, trademarks, service marks, or product names of the Licensor,
 *           except as required for reasonable and customary use in describing the
 *           origin of the Work and reproducing the content of the NOTICE file.
 *
 *        7. Disclaimer of Warranty. Unless required by applicable law or
 *           agreed to in writing, Licensor provides the Work (and each
 *           Contributor provides its Contributions) on an "AS IS" BASIS,
 *           WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *           implied, including, without limitation, any warranties or conditions
 *           of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A
 *           PARTICULAR PURPOSE. You are solely responsible for determining the
 *           appropriateness of using or redistributing the Work and assume any
 *           risks associated with Your exercise of permissions under this License.
 *
 *        8. Limitation of Liability. In no event and under no legal theory,
 *           whether in tort (including negligence), contract, or otherwise,
 *           unless required by applicable law (such as deliberate and grossly
 *           negligent acts) or agreed to in writing, shall any Contributor be
 *           liable to You for damages, including any direct, indirect, special,
 *           incidental, or consequential damages of any character arising as a
 *           result of this License or out of the use or inability to use the
 *           Work (including but not limited to damages for loss of goodwill,
 *           work stoppage, computer failure or malfunction, or any and all
 *           other commercial damages or losses), even if such Contributor
 *           has been advised of the possibility of such damages.
 *
 *        9. Accepting Warranty or Additional Liability. While redistributing
 *           the Work or Derivative Works thereof, You may choose to offer,
 *           and charge a fee for, acceptance of support, warranty, indemnity,
 *           or other liability obligations and/or rights consistent with this
 *           License. However, in accepting such obligations, You may act only
 *           on Your own behalf and on Your sole responsibility, not on behalf
 *           of any other Contributor, and only if You agree to indemnify,
 *           defend, and hold each Contributor harmless for any liability
 *           incurred by, or claims asserted against, such Contributor by reason
 *           of your accepting any such warranty or additional liability.
 *
 *        END OF TERMS AND CONDITIONS
 *
 *        APPENDIX: How to apply the Apache License to your work.
 *
 *           To apply the Apache License to your work, attach the following
 *           boilerplate notice, with the fields enclosed by brackets "[]"
 *           replaced with your own identifying information. (Don't include
 *           the brackets!)  The text should be enclosed in the appropriate
 *           comment syntax for the file format. We also recommend that a
 *           file or class name and description of purpose be included on the
 *           same "printed page" as the copyright notice for easier
 *           identification within third-party archives.
 *
 *        Copyright 2016 ShenHuaJie iBase4J@163.com
 *
 *        Licensed under the Apache License, Version 2.0 (the "License");
 *        you may not use this file except in compliance with the License.
 *        You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 *        Unless required by applicable law or agreed to in writing, software
 *        distributed under the License is distributed on an "AS IS" BASIS,
 *        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *        See the License for the specific language governing permissions and
 *        limitations under the License.
 *
 *     ======================================================================
 *
 *                                             Apache许可证
 *                                         版本 2.0，2004年1月
 *                                     http://www.apache.org/licenses/
 *
 *        使用、重生成及分发的术语和条件：
 *
 *        1.定义
 *
 *          "许可证"是指根据本文档第1到第9部分关于使用、重生成和分发的术语和条件。
 *
 *          "许可证颁发者"是指版权所有者或者由版权所有者批准的授权许可证的实体。
 *
 *          "法律实体"是指实施实体和进行控制的所有其它实体受该实体控制，或者受该实体集中控制。
 *          根据此定义，"控制"是指(i)让无论是否签订协议的上述实体，进行指导或管理的直接权利或间接权利，
 *          或者(ii)拥有百分之五十(50%)或以上已发行股票的所有者，或者(iii)上述实体的实权所有者。
 *
 *          "用户"(或"用户的")是指行使本许可证所授予权限的个人或法律实体。
 *
 *          "源程序"形式是指对包含但不限制软件源代码、文档源程序和配置文件进行修改的首选形式。
 *
 *          "目标"形式是指对源程序形式进行机械转换或翻译的任何形式，包括但不限于对编译的目标代码，
 *          生成的文件以及转换为其它媒体类型。
 *
 *          "作品"是指根据本许可证所制作的源程序形式或目标形式的著作，在著作中包含的或附加的版权通知
 *          (在下面附录中提供了一个示例)。
 *
 *          "衍生作品"是指基于作品(或从作品衍生而来)的源程序形式或目标形式的任何作品，以及编辑修订、
 *          注释、详细描述或其它修订等构成原创著作作品的整体。根据本许可证，衍生作品不得包括与作品及其
 *          衍生作品分离之作品，或仅与作品及其衍生作品的接口相链接(或按名称结合)之作品。
 *
 *          "贡献"是指任何著作作品，包括作品的原始版本和对该作品或衍生作品所做的任何修订或补充，
 *          意在提交给许可证颁发者以让版权所有者或代表版权所有者的授权个人或法律实体包含在其作品中。
 *          根据此定义，"提交"一词表示发送给许可证颁发者或其代表人，任何电子的、口头的或书面的交流信息形式，
 *          包括但不限于在由许可证颁发者或者代表其管理的电子邮件清单、源代码控制系统、以及发布跟踪系统上为
 *          讨论和提高作品的交流，但不包括由版权所有者以书面形式明显标注或指定为"非贡献"的交流活动。
 *
 *          "贡献者"是指许可证颁发者和代表从许可证颁发者接受之贡献的并随后包含在作品之贡献中的任何个人或法律实体。
 *
 *        2.版权许可证的授予
 *
 *          根据本许可证的条款，每个贡献者授予用户永久性的、全球性的、非专有性的、免费的、无版权费的、
 *          不可撤销的版权许可证以源程序形式或目标形式复制、准备衍生作品、公开显示、公开执行、
 *          授予分许可证、以及分发作品和这样的衍生作品。
 *
 *        3.专利许可证的授予
 *
 *          根据本许可证的条款，每个贡献者授予用户永久性的、全球性的、非专有性的、免费的、无版权费的、
 *          不可撤销的(除在本部分进行说明)专利许可证对作品进行制作、让人制作、使用、提供销售、销售、
 *          进口和其它转让，且这样的许可证仅适用于在所递交作品的贡献中因可由单一的或多个这样的贡献者
 *          授予而必须侵犯的申请专利。如果用户对任何实体针对作品或作品中所涉及贡献提出因直接性或贡献性
 *          专利侵权而提起专利法律诉讼(包括交互诉讼请求或反索赔)，那么根据本许可证，授予用户针对作品
 *          的任何专利许可证将在提起上述诉讼之日起终止。
 *
 *        4.重新分发
 *
 *          用户可在任何媒介中复制和分发作品或衍生作品之副本，无论是否修订，还是以源程序形式或目标形式，
 *          条件是用户需满足下列条款：
 *
 *          a) 用户必须为作品或衍生作品的任何其他接收者提供本许可证的副本；并且
 *
 *          b) 用户必须让任何修改过的文件附带明显的通知，声明用户已更改文件；并且
 *
 *          c) 用户必须从作品的源程序形式中保留衍生作品源程序形式的用户所分发的所有版权、专利、
 *             商标和属性通知，但不包括不属于衍生作品任何部分的类似通知；并且
 *
 *          d) 如果作品将"通知"文本文件包括为其分发作品的一部分，那么用户分发的任何衍生作品中须至少
 *             在下列地方之一包括，在这样的通知文件中所包含的属性通知的可读副本，但不包括那些不属于衍生
 *     	    作品任何部分的通知：在作为衍生作品一部分而分发的通知文本文件中；如果与衍生作品一起提供则
 *     	    在源程序形式或文件中；或者通常作为第三方通知出现的时候和地方，在衍生作品中产生的画面中。
 *     	    通知文件的内容仅供信息提供，并未对许可证进行修改。用户可在其分发的衍生作品中在作品的通知
 *     	    文本后或作为附录添加自己的属性通知，条件是附加的属性通知不得构成修改本许可证。
 *
 *          用户可以为自身所做出的修订添加自己的版权声明并可对自身所做出修订内容或为这样的衍生作品作为
 *          整体的使用、复制或分发提供附加或不同的条款，条件是用户对作品的使用、复制和分发必须符合本许
 *          可证中声明的条款。
 *
 *        5.贡献的提交。
 *
 *          除非用户明确声明，在作品中由用户向许可证颁发者的提交若要包含在贡献中，必须在无任何附加条款下
 *          符合本许可证的条款。尽管上面如此规定，执行许可证颁发者有关贡献的条款时，任何情况下均不得替代
 *          或修改任何单独许可证协议的条款。
 *
 *        6.商标。本许可证并未授予用户使用许可证颁发者的商号、商标、服务标记或产品名称，除非将这些名称
 *          用于合理性和惯例性描述作品起源和复制通知文件的内容时。
 *
 *        7.保证否认条款。除非因适用法律需要或书面同意，许可证颁发者以"按原样"基础提供作品(并且每个
 *          贡献者提供其贡献)，无任何明示的或暗示的保证或条件，包括但不限于关于所有权、不侵权、
 *     	 商品适销性、或适用性的保证或条件。用户仅对使用或重新分发作品的正确性负责，并需承担根据本
 *     	 许可证行使权限时的任何风险。
 *
 *        8.责任限制条款。在任何情况下并根据任何法律，无论是因侵权(包括过失)或根据合同，还是其它原因，
 *          除非根据适用法律需要(例如故意行为和重大过失行为)或经书面同意，即使贡献者事先已被告知发生
 *          损害的可能性，任何贡献者不就用户因使用本许可证或不能使用或无法使用作品(包括但不限于商誉损失、
 *          停工、计算机失效或故障，或任何商业损坏或损失)而造成的损失，包括直接的、非直接的、特殊的、意外
 *          的或间接的字符损坏而负责。
 *
 *        9.接受保证或附加责任。重新分发作品或及其衍生作品时，用户可选择提供或为符合本许可证承担之支持、
 *          担保、赔偿或其它职责义务和/或权利而收取费用。但是，在承担上述义务时，用户只可代表用户本身和
 *          用户本身责任来执行，无需代表任何其它贡献者，并且用户仅可保证、防护并保持每个贡献者不受任何
 *          因此而产生的责任或对因用户自身承担这样的保证或附加责任而对这样的贡献者所提出的索赔。
 *
 *        条款结束
 *
 *        附录：如何向用户作品中应用Apache许可证。
 *
 *        若要向用户作品应用Apache许可证，请附加下列样本通知，将括号"[]"中的字段以用户自身的
 *        区分信息来替换(但不包括括号)。文本必须以文件格式适当的注释句法包含在其中。
 *        另外建议将文件名或类别名以及目的说明包含在相同的"打印页"上作为版权通知，以更加容易的区分出第三方档案。
 *
 *        版权所有 2016 ShenHuaJie iBase4J@163.com 根据2.0版本Apache许可证("许可证")授权；
 *        根据本许可证，用户可以不使用此文件。
 *
 *        用户可从下列网址获得许可证副本：http://www.apache.org/licenses/LICENSE-2.0
 *        除非因适用法律需要或书面同意，根据许可证分发的软件是基于"按原样"基础提供，
 *        无任何明示的或暗示的保证或条件。详见根据许可证许可下，特定语言的管辖权限和限制。
 *
 *        =======================================================
 *
 *        简要解释：
 *
 *        1.需要给代码的用户一份Apache Licence
 *        2.如果你修改了代码，需要在被修改的文件中说明。
 *        3.在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，
 *          专利声明和其他原来作者规定需要包含的说明。
 *        4.如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有 Apache Licence。
 *          你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。
 * ====
 *
 * #set( $symbol_pound = '#' )
 * #set( $symbol_dollar = '$' )
 * #set( $symbol_escape = '\' )
 *
 *                                  Apache License
 *                            Version 2.0, January 2004
 *                         http://www.apache.org/licenses/
 *
 *    TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 *
 *    1. Definitions.
 *
 *       "License" shall mean the terms and conditions for use, reproduction,
 *       and distribution as defined by Sections 1 through 9 of this document.
 *
 *       "Licensor" shall mean the copyright owner or entity authorized by
 *       the copyright owner that is granting the License.
 *
 *       "Legal Entity" shall mean the union of the acting entity and all
 *       other entities that control, are controlled by, or are under common
 *       control with that entity. For the purposes of this definition,
 *       "control" means (i) the power, direct or indirect, to cause the
 *       direction or management of such entity, whether by contract or
 *       otherwise, or (ii) ownership of fifty percent (50%) or more of the
 *       outstanding shares, or (iii) beneficial ownership of such entity.
 *
 *       "You" (or "Your") shall mean an individual or Legal Entity
 *       exercising permissions granted by this License.
 *
 *       "Source" form shall mean the preferred form for making modifications,
 *       including but not limited to software source code, documentation
 *       source, and configuration files.
 *
 *       "Object" form shall mean any form resulting from mechanical
 *       transformation or translation of a Source form, including but
 *       not limited to compiled object code, generated documentation,
 *       and conversions to other media types.
 *
 *       "Work" shall mean the work of authorship, whether in Source or
 *       Object form, made available under the License, as indicated by a
 *       copyright notice that is included in or attached to the work
 *       (an example is provided in the Appendix below).
 *
 *       "Derivative Works" shall mean any work, whether in Source or Object
 *       form, that is based on (or derived from) the Work and for which the
 *       editorial revisions, annotations, elaborations, or other modifications
 *       represent, as a whole, an original work of authorship. For the purposes
 *       of this License, Derivative Works shall not include works that remain
 *       separable from, or merely link (or bind by name) to the interfaces of,
 *       the Work and Derivative Works thereof.
 *
 *       "Contribution" shall mean any work of authorship, including
 *       the original version of the Work and any modifications or additions
 *       to that Work or Derivative Works thereof, that is intentionally
 *       submitted to Licensor for inclusion in the Work by the copyright owner
 *       or by an individual or Legal Entity authorized to submit on behalf of
 *       the copyright owner. For the purposes of this definition, "submitted"
 *       means any form of electronic, verbal, or written communication sent
 *       to the Licensor or its representatives, including but not limited to
 *       communication on electronic mailing lists, source code control systems,
 *       and issue tracking systems that are managed by, or on behalf of, the
 *       Licensor for the purpose of discussing and improving the Work, but
 *       excluding communication that is conspicuously marked or otherwise
 *       designated in writing by the copyright owner as "Not a Contribution."
 *
 *       "Contributor" shall mean Licensor and any individual or Legal Entity
 *       on behalf of whom a Contribution has been received by Licensor and
 *       subsequently incorporated within the Work.
 *
 *    2. Grant of Copyright License. Subject to the terms and conditions of
 *       this License, each Contributor hereby grants to You a perpetual,
 *       worldwide, non-exclusive, no-charge, royalty-free, irrevocable
 *       copyright license to reproduce, prepare Derivative Works of,
 *       publicly display, publicly perform, sublicense, and distribute the
 *       Work and such Derivative Works in Source or Object form.
 *
 *    3. Grant of Patent License. Subject to the terms and conditions of
 *       this License, each Contributor hereby grants to You a perpetual,
 *       worldwide, non-exclusive, no-charge, royalty-free, irrevocable
 *       (except as stated in this section) patent license to make, have made,
 *       use, offer to sell, sell, import, and otherwise transfer the Work,
 *       where such license applies only to those patent claims licensable
 *       by such Contributor that are necessarily infringed by their
 *       Contribution(s) alone or by combination of their Contribution(s)
 *       with the Work to which such Contribution(s) was submitted. If You
 *       institute patent litigation against any entity (including a
 *       cross-claim or counterclaim in a lawsuit) alleging that the Work
 *       or a Contribution incorporated within the Work constitutes direct
 *       or contributory patent infringement, then any patent licenses
 *       granted to You under this License for that Work shall terminate
 *       as of the date such litigation is filed.
 *
 *    4. Redistribution. You may reproduce and distribute copies of the
 *       Work or Derivative Works thereof in any medium, with or without
 *       modifications, and in Source or Object form, provided that You
 *       meet the following conditions:
 *
 *       (a) You must give any other recipients of the Work or
 *           Derivative Works a copy of this License; and
 *
 *       (b) You must cause any modified files to carry prominent notices
 *           stating that You changed the files; and
 *
 *       (c) You must retain, in the Source form of any Derivative Works
 *           that You distribute, all copyright, patent, trademark, and
 *           attribution notices from the Source form of the Work,
 *           excluding those notices that do not pertain to any part of
 *           the Derivative Works; and
 *
 *       (d) If the Work includes a "NOTICE" text file as part of its
 *           distribution, then any Derivative Works that You distribute must
 *           include a readable copy of the attribution notices contained
 *           within such NOTICE file, excluding those notices that do not
 *           pertain to any part of the Derivative Works, in at least one
 *           of the following places: within a NOTICE text file distributed
 *           as part of the Derivative Works; within the Source form or
 *           documentation, if provided along with the Derivative Works; or,
 *           within a display generated by the Derivative Works, if and
 *           wherever such third-party notices normally appear. The contents
 *           of the NOTICE file are for informational purposes only and
 *           do not modify the License. You may add Your own attribution
 *           notices within Derivative Works that You distribute, alongside
 *           or as an addendum to the NOTICE text from the Work, provided
 *           that such additional attribution notices cannot be construed
 *           as modifying the License.
 *
 *       You may add Your own copyright statement to Your modifications and
 *       may provide additional or different license terms and conditions
 *       for use, reproduction, or distribution of Your modifications, or
 *       for any such Derivative Works as a whole, provided Your use,
 *       reproduction, and distribution of the Work otherwise complies with
 *       the conditions stated in this License.
 *
 *    5. Submission of Contributions. Unless You explicitly state otherwise,
 *       any Contribution intentionally submitted for inclusion in the Work
 *       by You to the Licensor shall be under the terms and conditions of
 *       this License, without any additional terms or conditions.
 *       Notwithstanding the above, nothing herein shall supersede or modify
 *       the terms of any separate license agreement you may have executed
 *       with Licensor regarding such Contributions.
 *
 *    6. Trademarks. This License does not grant permission to use the trade
 *       names, trademarks, service marks, or product names of the Licensor,
 *       except as required for reasonable and customary use in describing the
 *       origin of the Work and reproducing the content of the NOTICE file.
 *
 *    7. Disclaimer of Warranty. Unless required by applicable law or
 *       agreed to in writing, Licensor provides the Work (and each
 *       Contributor provides its Contributions) on an "AS IS" BASIS,
 *       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *       implied, including, without limitation, any warranties or conditions
 *       of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A
 *       PARTICULAR PURPOSE. You are solely responsible for determining the
 *       appropriateness of using or redistributing the Work and assume any
 *       risks associated with Your exercise of permissions under this License.
 *
 *    8. Limitation of Liability. In no event and under no legal theory,
 *       whether in tort (including negligence), contract, or otherwise,
 *       unless required by applicable law (such as deliberate and grossly
 *       negligent acts) or agreed to in writing, shall any Contributor be
 *       liable to You for damages, including any direct, indirect, special,
 *       incidental, or consequential damages of any character arising as a
 *       result of this License or out of the use or inability to use the
 *       Work (including but not limited to damages for loss of goodwill,
 *       work stoppage, computer failure or malfunction, or any and all
 *       other commercial damages or losses), even if such Contributor
 *       has been advised of the possibility of such damages.
 *
 *    9. Accepting Warranty or Additional Liability. While redistributing
 *       the Work or Derivative Works thereof, You may choose to offer,
 *       and charge a fee for, acceptance of support, warranty, indemnity,
 *       or other liability obligations and/or rights consistent with this
 *       License. However, in accepting such obligations, You may act only
 *       on Your own behalf and on Your sole responsibility, not on behalf
 *       of any other Contributor, and only if You agree to indemnify,
 *       defend, and hold each Contributor harmless for any liability
 *       incurred by, or claims asserted against, such Contributor by reason
 *       of your accepting any such warranty or additional liability.
 *
 *    END OF TERMS AND CONDITIONS
 *
 *    APPENDIX: How to apply the Apache License to your work.
 *
 *       To apply the Apache License to your work, attach the following
 *       boilerplate notice, with the fields enclosed by brackets "[]"
 *       replaced with your own identifying information. (Don't include
 *       the brackets!)  The text should be enclosed in the appropriate
 *       comment syntax for the file format. We also recommend that a
 *       file or class name and description of purpose be included on the
 *       same "printed page" as the copyright notice for easier
 *       identification within third-party archives.
 *
 *    Copyright 2016 ShenHuaJie iBase4J@163.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * ======================================================================
 *
 *                                         Apache许可证
 *                                     版本 2.0，2004年1月
 *                                 http://www.apache.org/licenses/
 *
 *    使用、重生成及分发的术语和条件：
 *
 *    1.定义
 *
 *      "许可证"是指根据本文档第1到第9部分关于使用、重生成和分发的术语和条件。
 *
 *      "许可证颁发者"是指版权所有者或者由版权所有者批准的授权许可证的实体。
 *
 *      "法律实体"是指实施实体和进行控制的所有其它实体受该实体控制，或者受该实体集中控制。
 *      根据此定义，"控制"是指(i)让无论是否签订协议的上述实体，进行指导或管理的直接权利或间接权利，
 *      或者(ii)拥有百分之五十(50%)或以上已发行股票的所有者，或者(iii)上述实体的实权所有者。
 *
 *      "用户"(或"用户的")是指行使本许可证所授予权限的个人或法律实体。
 *
 *      "源程序"形式是指对包含但不限制软件源代码、文档源程序和配置文件进行修改的首选形式。
 *
 *      "目标"形式是指对源程序形式进行机械转换或翻译的任何形式，包括但不限于对编译的目标代码，
 *      生成的文件以及转换为其它媒体类型。
 *
 *      "作品"是指根据本许可证所制作的源程序形式或目标形式的著作，在著作中包含的或附加的版权通知
 *      (在下面附录中提供了一个示例)。
 *
 *      "衍生作品"是指基于作品(或从作品衍生而来)的源程序形式或目标形式的任何作品，以及编辑修订、
 *      注释、详细描述或其它修订等构成原创著作作品的整体。根据本许可证，衍生作品不得包括与作品及其
 *      衍生作品分离之作品，或仅与作品及其衍生作品的接口相链接(或按名称结合)之作品。
 *
 *      "贡献"是指任何著作作品，包括作品的原始版本和对该作品或衍生作品所做的任何修订或补充，
 *      意在提交给许可证颁发者以让版权所有者或代表版权所有者的授权个人或法律实体包含在其作品中。
 *      根据此定义，"提交"一词表示发送给许可证颁发者或其代表人，任何电子的、口头的或书面的交流信息形式，
 *      包括但不限于在由许可证颁发者或者代表其管理的电子邮件清单、源代码控制系统、以及发布跟踪系统上为
 *      讨论和提高作品的交流，但不包括由版权所有者以书面形式明显标注或指定为"非贡献"的交流活动。
 *
 *      "贡献者"是指许可证颁发者和代表从许可证颁发者接受之贡献的并随后包含在作品之贡献中的任何个人或法律实体。
 *
 *    2.版权许可证的授予
 *
 *      根据本许可证的条款，每个贡献者授予用户永久性的、全球性的、非专有性的、免费的、无版权费的、
 *      不可撤销的版权许可证以源程序形式或目标形式复制、准备衍生作品、公开显示、公开执行、
 *      授予分许可证、以及分发作品和这样的衍生作品。
 *
 *    3.专利许可证的授予
 *
 *      根据本许可证的条款，每个贡献者授予用户永久性的、全球性的、非专有性的、免费的、无版权费的、
 *      不可撤销的(除在本部分进行说明)专利许可证对作品进行制作、让人制作、使用、提供销售、销售、
 *      进口和其它转让，且这样的许可证仅适用于在所递交作品的贡献中因可由单一的或多个这样的贡献者
 *      授予而必须侵犯的申请专利。如果用户对任何实体针对作品或作品中所涉及贡献提出因直接性或贡献性
 *      专利侵权而提起专利法律诉讼(包括交互诉讼请求或反索赔)，那么根据本许可证，授予用户针对作品
 *      的任何专利许可证将在提起上述诉讼之日起终止。
 *
 *    4.重新分发
 *
 *      用户可在任何媒介中复制和分发作品或衍生作品之副本，无论是否修订，还是以源程序形式或目标形式，
 *      条件是用户需满足下列条款：
 *
 *      a) 用户必须为作品或衍生作品的任何其他接收者提供本许可证的副本；并且
 *
 *      b) 用户必须让任何修改过的文件附带明显的通知，声明用户已更改文件；并且
 *
 *      c) 用户必须从作品的源程序形式中保留衍生作品源程序形式的用户所分发的所有版权、专利、
 *         商标和属性通知，但不包括不属于衍生作品任何部分的类似通知；并且
 *
 *      d) 如果作品将"通知"文本文件包括为其分发作品的一部分，那么用户分发的任何衍生作品中须至少
 *         在下列地方之一包括，在这样的通知文件中所包含的属性通知的可读副本，但不包括那些不属于衍生
 * 	    作品任何部分的通知：在作为衍生作品一部分而分发的通知文本文件中；如果与衍生作品一起提供则
 * 	    在源程序形式或文件中；或者通常作为第三方通知出现的时候和地方，在衍生作品中产生的画面中。
 * 	    通知文件的内容仅供信息提供，并未对许可证进行修改。用户可在其分发的衍生作品中在作品的通知
 * 	    文本后或作为附录添加自己的属性通知，条件是附加的属性通知不得构成修改本许可证。
 *
 *      用户可以为自身所做出的修订添加自己的版权声明并可对自身所做出修订内容或为这样的衍生作品作为
 *      整体的使用、复制或分发提供附加或不同的条款，条件是用户对作品的使用、复制和分发必须符合本许
 *      可证中声明的条款。
 *
 *    5.贡献的提交。
 *
 *      除非用户明确声明，在作品中由用户向许可证颁发者的提交若要包含在贡献中，必须在无任何附加条款下
 *      符合本许可证的条款。尽管上面如此规定，执行许可证颁发者有关贡献的条款时，任何情况下均不得替代
 *      或修改任何单独许可证协议的条款。
 *
 *    6.商标。本许可证并未授予用户使用许可证颁发者的商号、商标、服务标记或产品名称，除非将这些名称
 *      用于合理性和惯例性描述作品起源和复制通知文件的内容时。
 *
 *    7.保证否认条款。除非因适用法律需要或书面同意，许可证颁发者以"按原样"基础提供作品(并且每个
 *      贡献者提供其贡献)，无任何明示的或暗示的保证或条件，包括但不限于关于所有权、不侵权、
 * 	 商品适销性、或适用性的保证或条件。用户仅对使用或重新分发作品的正确性负责，并需承担根据本
 * 	 许可证行使权限时的任何风险。
 *
 *    8.责任限制条款。在任何情况下并根据任何法律，无论是因侵权(包括过失)或根据合同，还是其它原因，
 *      除非根据适用法律需要(例如故意行为和重大过失行为)或经书面同意，即使贡献者事先已被告知发生
 *      损害的可能性，任何贡献者不就用户因使用本许可证或不能使用或无法使用作品(包括但不限于商誉损失、
 *      停工、计算机失效或故障，或任何商业损坏或损失)而造成的损失，包括直接的、非直接的、特殊的、意外
 *      的或间接的字符损坏而负责。
 *
 *    9.接受保证或附加责任。重新分发作品或及其衍生作品时，用户可选择提供或为符合本许可证承担之支持、
 *      担保、赔偿或其它职责义务和/或权利而收取费用。但是，在承担上述义务时，用户只可代表用户本身和
 *      用户本身责任来执行，无需代表任何其它贡献者，并且用户仅可保证、防护并保持每个贡献者不受任何
 *      因此而产生的责任或对因用户自身承担这样的保证或附加责任而对这样的贡献者所提出的索赔。
 *
 *    条款结束
 *
 *    附录：如何向用户作品中应用Apache许可证。
 *
 *    若要向用户作品应用Apache许可证，请附加下列样本通知，将括号"[]"中的字段以用户自身的
 *    区分信息来替换(但不包括括号)。文本必须以文件格式适当的注释句法包含在其中。
 *    另外建议将文件名或类别名以及目的说明包含在相同的"打印页"上作为版权通知，以更加容易的区分出第三方档案。
 *
 *    版权所有 2016 ShenHuaJie iBase4J@163.com 根据2.0版本Apache许可证("许可证")授权；
 *    根据本许可证，用户可以不使用此文件。
 *
 *    用户可从下列网址获得许可证副本：http://www.apache.org/licenses/LICENSE-2.0
 *    除非因适用法律需要或书面同意，根据许可证分发的软件是基于"按原样"基础提供，
 *    无任何明示的或暗示的保证或条件。详见根据许可证许可下，特定语言的管辖权限和限制。
 *
 *    =======================================================
 *
 *    简要解释：
 *
 *    1.需要给代码的用户一份Apache Licence
 *    2.如果你修改了代码，需要在被修改的文件中说明。
 *    3.在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，
 *      专利声明和其他原来作者规定需要包含的说明。
 *    4.如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有 Apache Licence。
 *      你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。
 */
package com.lzq.catalog.other;

import cn.hutool.core.util.ReflectUtil;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.StatementSet;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 * 验证flink iceberg 相关的功能
 * Company: www.dtstack.com
 * @author wujuan
 * @date 2022-01-18
 */
public class IcebergCatalogSPIDemo {
    private static final Logger LOG = LoggerFactory.getLogger(IcebergCatalogSPIDemo.class);

    EnvironmentSettings settings;
    StreamExecutionEnvironment env;
    TableEnvironment tableEnv;
    StatementSet statementSet;

    @Rule
    public final EnvironmentVariables environmentVariables
            = new EnvironmentVariables();


    @Before
    public void initStreamEnv() {
        settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .useBlinkPlanner()
                .build();
        tableEnv = TableEnvironment.create(settings);

        //
        ////构建环境信息
        //env = StreamExecutionEnvironment.getExecutionEnvironment();
        //env.setParallelism(1);
        //tableEnv = StreamTableEnvironment.create(env, settings);
        //statementSet = tableEnv.createStatementSet();
        //env.setParallelism(1);
        //tableEnv.getConfig().getConfiguration().setString(PipelineOptions.NAME, "wujuan_job");
        System.out.println("初始化 Flink 环境成功!");

        environmentVariables.set("HADOOP_HOME", "");
        //environmentVariables.set("HADOOP_CONF_DIR", "");
        //environmentVariables.set("YARN_CONF_DIR", "");

        environmentVariables.set("HADOOP_CONF_DIR", "/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf");
        environmentVariables.set("YARN_CONF_DIR", "/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf");
        environmentVariables.set("HADOOP_USER_NAME", "root");

        System.out.println(Thread.currentThread().getName());


    }
    @Test
    public void testIcebergCatalog(){


        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/flinkx-dist/catalog/hive/flinkx-catalog-master.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }



        System.out.println(Thread.currentThread().getName());
        String icebergCatalog = "CREATE CATALOG iceberg_hive WITH (\n" +
                "    'type'='iceberg',\n" +
                "    'catalog-type'='hive',\n" +
                "    'uri'='thrift://hadoop3x3:9083',\n" +
                "    'clients'='2',\n" +
                "    'iceberg.format.version'='2',\n" +
                "    'property-version'='1',\n" +
                "    'hive-conf-dir' = '/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf',\n"+
                "    'warehouse'='hdfs://ns//user/hive/warehouse'\n" +
                ")";

        tableEnv.executeSql(icebergCatalog);


        Optional<Catalog> flink_hive_catalog_11111 = tableEnv.getCatalog("iceberg_hive");

        List<String> strings = flink_hive_catalog_11111.get().listDatabases();
        strings.forEach( db -> {
            System.out.println("------------------------------------------ " + db + " -------------------------------------------");
            List<String> tables = null;
            try {
                tables = flink_hive_catalog_11111.get().listTables(db);
            } catch (DatabaseNotExistException e) {
                throw new RuntimeException(e);
            }
            tables.forEach(t -> {
                System.out.println(t);
            });
        });

        try {
            System.out.println(flink_hive_catalog_11111.get().getDatabase("wujuan"));
        } catch (DatabaseNotExistException e) {
            throw new RuntimeException(e);
        }

        //
        //String createSinkTable = "CREATE TABLE IF NOT EXISTS iceberg_hive.luna.sink5 (\n" +
        //        "    order_id INTEGER COMMENT 'unique id',\n" +
        //        "          `order_date` DATE ,\n" +
        //        "          `order_time` TIMESTAMP(3),\n" +
        //        "          `quantity` INT ,\n" +
        //        "          `product_id` INT ,\n" +
        //        "          `purchaser` STRING,\n" +
        //        "           primary key(order_id)  NOT ENFORCED" +
        //        ") with ('format-version' = '2')";
        //
        //String mysqlCDC = " CREATE TABLE demoOrders (\n" +
        //        "         `order_id` INTEGER ,\n" +
        //        "          `order_date` DATE ,\n" +
        //        "          `order_time` TIMESTAMP(3),\n" +
        //        "          `quantity` INT ,\n" +
        //        "          `product_id` INT ,\n" +
        //        "          `purchaser` STRING,\n" +
        //        "           primary key(order_id)  NOT ENFORCED" +
        //        "         ) WITH (\n" +
        //        "          'connector' = 'mysql-cdc',\n" +
        //        "          'hostname' = '172.16.83.218',\n" +
        //        "          'port' = '3306',\n" +
        //        "          'username' = 'drpeco',\n" +
        //        "          'password' = 'DT@Stack#123',\n" +
        //        "          'database-name' = 'test_mysql',\n" +
        //        "          'table-name' = 'demo_orders'," +
        //        //  全量 + 增量同步
        //        "          'scan.startup.mode' = 'initial'      " +
        //        " )";
        //
        //String exeSql = "insert into iceberg_hive.luna.sink5 select * from demoOrders";
        //
        //EnvironmentSettings settings = EnvironmentSettings
        //        .newInstance()
        //        .inStreamingMode()
        //        .useBlinkPlanner()
        //        .build();
        //
        ////构建环境信息
        //StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //env.enableCheckpointing(30 * 1000L);
        //
        //StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
        //StatementSet statementSet = tableEnv.createStatementSet();
        //
        //tableEnv.getConfig().getConfiguration().setString(PipelineOptions.NAME, "xc_job");
        //tableEnv.executeSql(mysqlCDC);
        //
        //tableEnv.executeSql(icebergCatalog);
        //tableEnv.executeSql(createSinkTable);
        //
        //statementSet.addInsertSql(exeSql);
        //TableResult tableResult = statementSet.execute();
        //tableResult.print();
        //
        //System.out.println("exe stmt");


    }

}
