<template>
    <div>
        <v-card-widget enableActions :title="'Cursos'">
            <div slot="widget-header-action">
                <v-text-field cols="4"
                              v-model="search"
                              append-icon="search"
                              label="Pesquisar"
                              single-line
                              hide-details
                ></v-text-field>
            </div>
            <div slot="widget-content">
                <v-dialog v-model="dialog" max-width="500px">
                    <template v-slot:activator="{on}">
                        <v-btn color="primary" class="mb-2" v-on="on">Novo Curso</v-btn>
                    </template>
                    <v-card>
                        <v-card-title>
                            <span class="headline">{{ formTitle }}</span>
                        </v-card-title>
                        <v-form ref="form" v-model="valid" :lazy-validation="lazy">
                            <v-card-text>
                                <v-container>
                                    <div v-if="dialogDelete === true">
                                        <v-row>
                                            <v-col cols="12" sm="6" md="12">
                                                Deseja deletar {{curso.nome}}?
                                            </v-col>
                                        </v-row>
                                    </div>
                                    <div v-else-if="dialogDelete === false">
                                        <v-row>
                                            <v-col cols="12" sm="6" md="12">
                                                <v-text-field type="text" v-model="curso.nome" label="Nome" :rules="[v => !!v || 'Nome é requerido']" required></v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="12">
                                                <v-text-field type="text" v-model="curso.sigla" label="Sigla" :rules="[v => !!v || 'Sigla é requerido']" required></v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="12">
                                                <v-select :items="items" v-model="curso.turno" label="Turno" :rules="[v => !!v || 'Turno é requerido']" required></v-select>
                                            </v-col>
                                        </v-row>
                                    </div>
                                </v-container>
                            </v-card-text>

                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <div v-if="dialogDelete === true">
                                    <v-btn color="blue darken-1" text @click="remove">Deletar</v-btn>
                                    <v-btn color="blue darken-1" text @click="close">Cancelar</v-btn>
                                </div>
                                <div v-else-if="dialogDelete === false">
                                    <v-btn color="blue darken-1" text @click="save" :disabled="!valid">Salvar</v-btn>
                                    <v-btn color="blue darken-1" text @click="close">Cancelar</v-btn>
                                </div>
                            </v-card-actions>
                        </v-form>
                    </v-card>
                </v-dialog>
                <v-row>
                    <v-col cols="12">
                        <v-data-table
                                :headers="headers"
                                :items="cursos"
                                :items-per-page="5"
                                :search="search"
                                :single-expand="singleExpand"
                                :expanded.sync="expanded"
                                item-key="name"
                                show-expand
                                class="elevation-1">
                            <template v-slot:item.action="{item}">
                                <v-icon medium="" color="success" class="mr-2" @click="editItem(item)">edit</v-icon>
                                <v-icon medium="" color="success" @click="deleteItem(item)">delete</v-icon>
                            </template>
                            <template v-slot:expanded-item="{ headers, item }">
                                <td :colspan="headers.length">
                                    <div v-for="user in listUsers(item.id)">
                                {{ user.nome}}
                                    </div>
                                </td>

                            </template>

                        </v-data-table>
                    </v-col>
                </v-row>
            </div>
        </v-card-widget>
    </div>
</template>

<script>
    // @ is an alias to /src
    import { mapState, mapActions } from 'vuex'
    import VCardWidget from "@/components/VWidget";
    //import {createNamespacedHelpers} from 'vuex'
    //const {mapState, mapActions} = createNamespacedHelpers('curso')

    export default {
        name: 'curso',
        components: {
            VCardWidget
        },
        data: () => ({
            items: ['Manhã', 'Tarde', 'Noite'],
            dialogDelete: false,
            dialog: false,
            valid: true,
            expanded: [],
            singleExpand: false,
            search: '',
            headers: [
                { text: 'Nome', align: 'left', value: 'nome' },
                { text: 'Sigla', value: 'sigla' },
                { text: 'Turno', value: 'turno' },
                { text: "Acões", value: "action", sortable: false, width: "8%" }
            ],
            cursoIndex: -1,
            limpar: {
                id: 0,
                nome: '',
                sigla: '',
                turno: ''
            },
            lazy: false,
        }),
        created() {
            this.listCurso()
        },
        computed: {
            formTitle () {
                if (this.dialogDelete) {
                    return "Deletar Curso";
                }else
                    return this.cursoIndex === -1 ? 'Novo Curso' : 'Editar Curso'
            },
            ...mapState('curso',['cursos', 'curso', 'usuarios', 'user']),

        },
        methods: {
            ...mapActions('curso',['listCurso', 'setCurso', 'addCurso', 'editarCurso', 'deletarCurso', 'listUsers']),

            editItem (item) {
                this.dialogDelete = false;
                this.cursoIndex = this.cursos.indexOf(item);
                this.setCurso(item);
                this.dialog = true;
            },
            deleteItem (item) {
                this.dialogDelete = true;
                this.cursoIndex = this.cursos.indexOf(item);
                this.setCurso(item);
                this.dialog = true;
            },
            close () {
                this.dialog = false;
                setTimeout(() => {
                    this.setCurso(this.limpar);
                    this.cursoIndex = -1;
                    this.dialogDelete = false;
                    this.$refs.form.resetValidation()
                }, 300);
            },
            save () {
                this.$refs.form.validate()
                if(this.cursoIndex > -1) {
                    Object.assign(this.cursos[this.cursoIndex], this.curso)
                    this.editarCurso()
                    this.$refs.form.resetValidation()
                }else{
                    this.addCurso()
                    this.$refs.form.resetValidation()
                }
                this.close()
            },
            remove() {
                this.cursos.splice(this.course, 1);
                this.deletarCurso();

                this.close();
            }
        }
    }
</script>
