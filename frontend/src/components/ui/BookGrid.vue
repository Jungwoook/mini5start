<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
            </div>
            <BooksByAuthor @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></BooksByAuthor>
            <AllBooks @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></AllBooks>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>AuthorId</th>
                        <th>BookTitle</th>
                        <th>Category</th>
                        <th>CreateDate</th>
                        <th>ModifyDate</th>
                        <th>BookSummary</th>
                        <th>BookCoverImage</th>
                        <th>BookContent</th>
                        <th>ViewCount</th>
                        <th>LikeCount</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="AuthorId">{{ val.authorId }}</td>
                            <td class="whitespace-nowrap" label="BookTitle">{{ val.bookTitle }}</td>
                            <td class="whitespace-nowrap" label="Category">{{ val.category }}</td>
                            <td class="whitespace-nowrap" label="CreateDate">{{ val.createDate }}</td>
                            <td class="whitespace-nowrap" label="ModifyDate">{{ val.modifyDate }}</td>
                            <td class="whitespace-nowrap" label="BookSummary">{{ val.bookSummary }}</td>
                            <td class="whitespace-nowrap" label="BookCoverImage">{{ val.bookCoverImage }}</td>
                            <td class="whitespace-nowrap" label="BookContent">{{ val.bookContent }}</td>
                            <td class="whitespace-nowrap" label="ViewCount">{{ val.viewCount }}</td>
                            <td class="whitespace-nowrap" label="LikeCount">{{ val.likeCount }}</td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Book 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Book :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Book 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="BookId" v-model="selectedRow.bookId" :editMode="true"/>
                            <Number label="AuthorId" v-model="selectedRow.authorId" :editMode="true"/>
                            <String label="BookTitle" v-model="selectedRow.bookTitle" :editMode="true"/>
                            <String label="Category" v-model="selectedRow.category" :editMode="true"/>
                            <Date label="CreateDate" v-model="selectedRow.createDate" :editMode="true"/>
                            <Date label="ModifyDate" v-model="selectedRow.modifyDate" :editMode="true"/>
                            <String label="BookSummary" v-model="selectedRow.bookSummary" :editMode="true"/>
                            <String label="BookCoverImage" v-model="selectedRow.bookCoverImage" :editMode="true"/>
                            <String label="BookContent" v-model="selectedRow.bookContent" :editMode="true"/>
                            <Number label="ViewCount" v-model="selectedRow.viewCount" :editMode="true"/>
                            <Number label="LikeCount" v-model="selectedRow.likeCount" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'bookGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'books',
    }),
    watch: {
    },
    methods:{
    }
}

</script>